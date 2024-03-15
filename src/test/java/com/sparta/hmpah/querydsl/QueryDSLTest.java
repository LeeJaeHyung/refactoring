package com.sparta.hmpah.querydsl;

import com.sparta.hmpah.entity.Comment;
import com.sparta.hmpah.entity.User;
import com.sparta.hmpah.repository.UserRepository;
import com.sparta.hmpah.service.CommentServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class QueryDSLTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  CommentServiceImpl commentService;

  @Test
  @DisplayName("QueryDSL 페이징 처리 테스트")
  void queryDSLTest(){
    //given
    User user =  userRepository.findById(1L).orElseThrow();
    Pageable pageable = PageRequest.of(1, 1);
    //when
    List<Comment> comments = commentService.queryDSL(user,"",pageable);
    List<Comment> comments2 = commentService.queryDSL2(user,"",pageable);
    //then
    for(Comment comment : comments){
      System.out.print("comment.getUser().getUsername() = " + comment.getUser().getUsername());
      System.out.println("   comment.getContent() = " + comment.getContent());
    }
    for(Comment comment : comments2){
      System.out.print("comment.getUser().getUsername() = " + comment.getUser().getUsername());
      System.out.println("   comment.getContent() = " + comment.getContent());
    }
  }
}
