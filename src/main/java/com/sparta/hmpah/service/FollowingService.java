package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.responseDto.FollowingResponse;
import com.sparta.hmpah.dto.responseDto.InfoResponse;
import com.sparta.hmpah.entity.User;
import java.util.List;



public interface FollowingService {

    /** 자신의 팔로잉 목록 조회.
     * @param user 팔로잉 정보 조회 요청자
     * @return 팔로잉 목록 정보
     */
    List<FollowingResponse> showFollowings(User user);

    /** 팔로잉 id 기준 팔로잉 목록 조회.
     * @param userId 대상자의 id 정보
     * @return id 대상자의 팔로잉 정보
     */
    List<FollowingResponse> showFollowings(Long userId);

    /** 팔로잉 id 대상자의 상세 정보 조회.
     * @param followingId 조회할 대상자의 id
     * @return 팔로잉 대상자의 상세정보
     */
    InfoResponse showFollowingInfo(Long followingId);

    /** 팔로잉 삭제
     * @param user 삭제 요청자 정보
     * @param followingId 삭제할 대상자 id
     * @return 팔로잉 삭제 정보
     */
    FollowingResponse deleteFollowing(User user,Long followingId);

    /** 팔로잉 생성 요청
     * @param user 팔로잉 생성 요청자 정보
     * @param followingId 팔로잉 추가할 대상 id
     * @return 팔로잉 생성 정보
     */
    FollowingResponse following(User user, Long followingId);
}
