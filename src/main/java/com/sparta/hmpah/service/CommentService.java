package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.requestDto.CommentLikeRequest;
import com.sparta.hmpah.dto.requestDto.CommentRequest;
import com.sparta.hmpah.dto.responseDto.CommentResponse;
import com.sparta.hmpah.entity.Comment;
import com.sparta.hmpah.entity.CommentLike;
import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.entity.User;
import com.sparta.hmpah.exception.CommentException;
import com.sparta.hmpah.repository.CommentLikeRepository;
import com.sparta.hmpah.repository.CommentRepository;
import com.sparta.hmpah.repository.PostRepository;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface CommentService {

  /**
   * postId 기준 모든 댓글 조회
   * @param postId 게시글에 id
   * @return postId 기준 모든 댓글
   */
  public List<CommentResponse> getComments(Long postId);

  /**
   * @param requestDto 댓글 생성 요청 정보
   * @param user 댓글 생성 요청자
   * @return 댓글 생성 결과
   */
  public CommentResponse createComment(CommentRequest requestDto,User user);

  /**
   * @param id 수정할 댓글 id
   * @param requestDto 수정될 댓글의 정보
   * @param user 댓글 수정 요청자
   * @return 댓글 수정 정보
   */
  public CommentResponse updateComment(Long id, CommentRequest requestDto, User user);

  /**
   * @param id 삭제할 댓글 id
   * @param user 댓글 삭제 요청자
   * @return 삭제후 postId 기준 모든 댓글
   */
  public List<CommentResponse> deleteComment(Long id, User user);

  /**
   * @param id 찾아올 댓글 id
   * @return 찾아온 댓글
   */
  public Comment findyComment(Long id);

  /**
   * @param postId PostId기준 댓글 삭제
   */
  public void deleteCommentByPostId(Long postId);

  /**
   * @param comment 가져온 댓글
   * @param user 현제 유저
   * @return 가져온 댓글과 현제 유저의 id를 비교하여 작성자인지 확인 하여 return
   */
  public boolean validateUsername(Comment comment, User user);

  /**
   * @param id 좋아요 갯수를 확인할 댓글 id
   * @return 좋아요 갯수를 return
   */
  public Long countByCommentId(Long id);

  /**
   * @param requestDto 좋아요 생성 요청 정보
   * @param user 좋아요 생성 요청자
   * @return 좋아요 갯수 return
   */
  public Long createCommentLike(CommentLikeRequest requestDto, User user);

  /**
   * @param commentId 좋아요 삭제할 댓글의 id
   * @param user 댓글 삭제 요청자
   * @return 좋아요 갯수 return
   */
  public Long deleteCommentLike(Long commentId, User user);
}
