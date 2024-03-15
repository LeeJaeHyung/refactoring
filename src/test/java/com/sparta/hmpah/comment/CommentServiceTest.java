package com.sparta.hmpah.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.hmpah.dto.requestDto.CommentRequest;
import com.sparta.hmpah.dto.responseDto.CommentResponse;
import com.sparta.hmpah.entity.Comment;
import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.entity.User;
import com.sparta.hmpah.repository.CommentLikeRepository;
import com.sparta.hmpah.repository.CommentRepository;
import com.sparta.hmpah.repository.PostRepository;
import com.sparta.hmpah.service.CommentService;
import com.sparta.hmpah.service.CommentServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

  @Mock
  PostRepository postRepository;

  @Mock
  CommentRepository commentRepository;

  @Mock
  CommentLikeRepository commentLikeRepository;

  @Mock
  MessageSource messageSource;

  @Test
  @DisplayName("updateComment")
  void test(){
    CommentService commentService = new CommentServiceImpl(postRepository,commentRepository,commentLikeRepository,messageSource);
    CommentRequest requestDto = new CommentRequest();
    String content = "내용수정";
    requestDto.setContent(content);
    User user = new User();
    Post post = new Post();
    user.setId(2L);
    post.setUser(user);
    Comment comment = new Comment(requestDto, user, post, 0);
    given(commentRepository.findById(2L)).willReturn(Optional.of(comment));
    CommentResponse result = commentService.updateComment(2L, requestDto ,user);
    assertEquals(content, result.getContent());
  }
}
