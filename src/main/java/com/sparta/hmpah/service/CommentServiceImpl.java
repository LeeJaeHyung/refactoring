package com.sparta.hmpah.service;

import com.mysema.commons.lang.IteratorAdapter;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.hmpah.dto.requestDto.CommentLikeRequest;
import com.sparta.hmpah.dto.requestDto.CommentRequest;
import com.sparta.hmpah.dto.responseDto.CommentResponse;
import com.sparta.hmpah.entity.Comment;
import com.sparta.hmpah.entity.CommentLike;
import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.entity.QComment;
import com.sparta.hmpah.entity.User;
import com.sparta.hmpah.exception.CommentException;
import com.sparta.hmpah.repository.CommentLikeRepository;
import com.sparta.hmpah.repository.CommentRepository;
import com.sparta.hmpah.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final CommentLikeRepository commentLikeRepository;
  private final MessageSource messageSource;
  @PersistenceContext
  EntityManager em;

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponse> getComments(Long postId) { // 게시글 id를 기준으로 속해있는 모든 댓글을 가져옴

    List<Comment> commentList = commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    return commentList.stream().map(CommentResponse::new).toList();
  }

  @Override
  public CommentResponse createComment(CommentRequest requestDto,User user) { // 댓글 생성
    Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new NullPointerException("해당 게시글을 찾을수 없습니다."));
    int position;//대댓글의 위치
    if(requestDto.getParentId()!=null){
      if(commentRepository.existsByParentId(requestDto.getParentId())){//대댓글이면서 이전에 작성된 대댓글이 존재
        List<Comment> childComments = commentRepository.findAllByParentIdOrderByPositionDesc(requestDto.getParentId());//position으로 orderby
        position = childComments.size()+1;
      }else position = 1;//대댓글 이나 이전에 작성된 대댓글 없음
    }else position = 0;//일반 댓글
    Comment comment = new Comment(requestDto, user, post, position);
    return new CommentResponse(commentRepository.save(comment));
  }

  @Override
  @Transactional
  public CommentResponse updateComment(Long id, CommentRequest requestDto,
      User user) { //댓글 id를 기준으로 댓글 update
    Comment comment = findyComment(id);
    if (validateUsername(comment, user)) { // 작성자와 로그인한 user가 일치할 경우에만 업데이트
      comment.update(requestDto);
      return new CommentResponse(comment);
    } else {
      throw new CommentException(
          messageSource.getMessage(
              "not.authority.update",
              null,
              "You don't have access",
              Locale.getDefault()
          )
      );
    }
  }

  @Override
  @Transactional
  public List<CommentResponse> deleteComment(Long id, User user) { //댓글 id를 기준으로 댓글삭제
    Comment comment = findyComment(id);//댓글 불러옴
    if (validateUsername(comment, user)) {//작성자 일치
      deleteChild(id);
    }
    return commentRepository.findByPostIdOrderByCreatedAtAsc(comment.getPost().getId()).stream().map(CommentResponse::new).toList();
  }

  @Override
  public Comment findyComment(Long id) {// id를 기준으로 댓글 찾아옴
    return commentRepository.findById(id)
        .orElseThrow(() -> new CommentException(
            messageSource.getMessage(
                "not.found.comment",
                null,
                "Not Found Comment",
                Locale.getDefault()
            )
        ));
  }

  @Override
  public boolean validateUsername(Comment comment, User user) {
    if (comment.getUser().getId().equals(user.getId())) {
      return true;
    }
    return false;
  }

  @Override
  public Long countByCommentId(Long id) {
    return commentLikeRepository.countByCommentId(id);
  }

  @Override
  public Long createCommentLike(CommentLikeRequest requestDto, User user) {
    Long commentId = requestDto.getCommentId();
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentException(
        messageSource.getMessage(
            "not.found.comment",
            null,
            "Not Found Comment",
            Locale.getDefault()
        )
    ));
    if (commentLikeState(comment, user)) {
      commentLikeRepository.save(new CommentLike(comment, user));
    }
    return countByCommentId(commentId);
  }

  @Override
  @Transactional
  public Long deleteCommentLike(Long commentId, User user) {//추천 삭제
    Long userId = user.getId();
    if (existsByCommentIdAndUserId(commentId, userId)) {//추천이 존재할 경우에만 삭제
      CommentLike commentLike = commentLikeRepository.findByCommentIdAndUserId(commentId, userId);
      commentLikeRepository.delete(commentLike);
    }
    return countByCommentId(commentId);
  }

  private boolean existsByCommentIdAndUserId(Long commentId, Long userId) { //이미 추천했다면 true 아니면 false
    return commentLikeRepository.existsByCommentIdAndUserId(commentId, userId);
  }


  private boolean checkWriter(Long commentId, Long userId) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 postId 입니다."));
    return userId.equals(comment.getUser().getId());
  }

  private boolean commentLikeState(Comment comment, User user) {
    return (!existsByCommentIdAndUserId(comment.getId(), user.getId()) && !checkWriter(
        comment.getId(), user.getId()));
  }

  private void deleteChild(Long id){
    if(commentRepository.existsByParentId(id)){//대댓글 존재
      List<Comment> childList = commentRepository.findAllByParentIdOrderByPositionDesc(id);
      for(Comment c : childList){
        deleteChild(c.getId());//재귀 호출로 하위 댓글에대한 모든 삭제 처리
      }
      deleting(id);//댓글 삭제;
    }else{
      deleting(id);// 하위 댓글이 없다면 삭제후 재귀호출의 중단
    }
  }

  private void deleting(Long id){
    commentRepository.deleteById(id);
    if(commentLikeRepository.existsByCommentId(id)){//댓글에 대한 좋아요 조회
      commentLikeRepository.deleteAllByCommentId(id);//좋아요 삭제
    }
  }

  public List<Comment> queryDSL(User user, String content, Pageable pageable){
    QComment comment = QComment.comment;
    var predicate = comment.content.like("%"+content+"%").and(comment.user.eq(user));
    var comments = commentRepository.findAll(predicate, pageable);
    return IteratorAdapter.asList(comments.iterator());
  }

  public List<Comment> queryDSL2(User user, String content, Pageable pageable){
    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
    QComment comment = QComment.comment;
    List<Comment> commentList = jpaQueryFactory.selectFrom(comment).where(comment.content.like("%"+content+"%").and(comment.user.eq(user))).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    return commentList;
  }
}
