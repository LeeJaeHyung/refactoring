package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.requestDto.PostRequest;
import com.sparta.hmpah.dto.responseDto.PostResponse;
import com.sparta.hmpah.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;


public interface PostService {

  /**
   * 팔로우 하는 유저의 게시글 목록을 조회
   * @param user 요청 유저정보
   * @return 해당 유저의 게시글 목록
   */
  List<PostResponse> getPostListByFollow(User user);

  /**
   * 참여중인 게시글 목록을 조회한다
   * @param user 조회할 유저 정보
   * @return 참여중인 게시글 반환
   */
  List<PostResponse> getPostListByMember(User user);

  /**
   * 자신의 게시글 목록을 조회
   * @param user 조회 요청 정보
   * @return 작성한 목록 게시글 반환
   */
  List<PostResponse> getMyPostList(User user);

  /**
   * 게시글 ID를 통해 게시글을 조회
   * @param postid 개시글 id
   * @param user 요청 유저
   * @return 게시글 반환
   */
  PostResponse getPostById(Long postid, User user);

  /**
   * 게시글을 작성한다
   * @param postRequest 게시글 생성 요청 정보
   * @param user 게시글 생성 요청자
   * @return 생성한 게시글 정보
   */
  PostResponse createPost(PostRequest postRequest, User user);

  /**
   * 게시글 ID를 통해 게시글을 수정
   * @param postid 수정할 게시글 id
   * @param postRequest 게시글 수정 요청 정보
   * @param user 게시글 수정 요청자
   * @return 게시글 수정 정보
   */
  PostResponse updatePost(Long postid, PostRequest postRequest, User user);

  /**
   *게시글 ID를 통해 게시글을 삭제
   * @param postid 삭제할 게시글 id
   * @param user 게시글 삭제 요청자
   * @return 게시글 삭제 정보
   */
  String deletePost(Long postid, User user);

  /**
   * 게시글 ID를 통해 게시글을 좋아요 생성
   * @param postid 좋아요할 게시글 id
   * @param user 게시글 좋아요 요청자
   * @return 게시글 좋아요 요청 결과 정보
   */
  String likePost(Long postid, User user);

  /**
   * 게시글 ID를 통해 게시글에 참여
   * @param postid 참여할 게시글 id
   * @param user 게시글 참여 요청자
   * @return 게시글 참여 요청 처리 정보
   */
  String joinPost(Long postid, User user);

  /**
   * 상태, 지역, 제목 옵션을 통해 게시글 목록을 조회
   *
   * @param status   상태 정보
   * @param location 지역 정보
   * @param title    게시글 제목
   * @param user     게시글 목록 조회 요청자
   * @param page     page
   * @param size     page 컷팅 사이즈
   * @param sortBy   정렬 대상
   * @param isAsc    정령 방식
   * @return 상태, 지역, 제목 옵션에 따른 목록 조회결과
   */
  Page getPostListByOption(String status, String location, String title, User user,
      int page, int size, String sortBy, boolean isAsc);
}
