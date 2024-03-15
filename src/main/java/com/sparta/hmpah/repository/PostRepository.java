package com.sparta.hmpah.repository;

import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
    Page<Post> findByStatusContainingAndLocationContainingAndTitleContaining(String status, String location, String title, Pageable pageable);
}
