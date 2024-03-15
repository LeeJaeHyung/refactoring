package com.sparta.hmpah.repository;

import com.sparta.hmpah.entity.Comment;
import com.sparta.hmpah.entity.Post;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommentRepository extends JpaRepository<Comment, Long>,QuerydslPredicateExecutor<Comment>  {

  List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);

  void deleteByPostId(Long postId);

  boolean existsByParentId(Long parentId);

  List<Comment> findAllByParentIdOrderByPositionDesc(Long parentId);

  List<Comment> findAllByPost(Post post);
}
