package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.responseDto.FollowerResponse;
import com.sparta.hmpah.dto.responseDto.InfoResponse;
import com.sparta.hmpah.entity.User;
import java.util.List;


public interface FollowerService {

    /** 자신의 팔로워 목록 조회
     * @param user 팔로워 목록 조회자 정보
     * @return 조회자의 팔로워 목록
     */
    List<FollowerResponse> showFollowers(User user);

    /** 대상자 팔로워 목록 조회
     * @param userId 팔로워 조회할 대상 id
     * @return 조회할 대상의 팔로워 목록
     */
    List<FollowerResponse> showFollowers(Long userId);

    /** 대상자의 상세 정보 조회
     * @param followerId 상세정보를 조회할 대상의 id
     * @return 상세정보를 조회할 대상의 상세정보
     */
    InfoResponse showFollowerInfo(Long followerId);

    /** 팔로워 삭제
     * @param user 팔로워를 삭제할 대상 정보
     * @param followerId 삭제할 팔로워 id
     * @return 팔로워를 삭제한 정보
     */
    FollowerResponse deleteFollower(User user,Long followerId);
}
