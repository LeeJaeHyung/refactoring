package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.requestDto.InfoRequest;
import com.sparta.hmpah.dto.responseDto.InfoResponse;
import com.sparta.hmpah.entity.User;


public interface InfoService {

    /**
     *유저 상세 정보 조회
     * @param user 대상 유저
     * @return 유저 상세 정보
     */
    InfoResponse showProfile(User user);

    /**
     * 유저 상세 정보 업데이트
     * @param user 대상 유저
     * @param profileRequest 업데이트할 상세정보 데이터
     * @return 업데이트 한 유저 상세정보
     */
    InfoResponse updateProfile(User user, InfoRequest profileRequest);
}
