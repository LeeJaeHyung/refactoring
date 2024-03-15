package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.requestDto.LoginInfoRequest;
import com.sparta.hmpah.dto.requestDto.SignupRequest;
import com.sparta.hmpah.entity.UserRoleEnum;

public interface UserService {

    /**
     * 로그인 요청
     * @param requestDto 로그인 요청 정보
     */
    void signup(SignupRequest requestDto);

    /**
     * 카톡유저 회원가입시 유저 닉네임 변경
     * @param id
     * @param additionalInfo 로그인 요청 정보
     */
    void updateKakaoUserNickname(Long id, LoginInfoRequest additionalInfo);

    /**
     * 유저 권환 확인
     * @param requestDto 유저 권한 인증 요청정보
     * @param role 권한정보
     * @return 유저 권한 정보
     */
    UserRoleEnum validAdmin(LoginInfoRequest requestDto, UserRoleEnum role);

}
