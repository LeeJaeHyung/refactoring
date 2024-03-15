package com.sparta.hmpah.service;

import com.sparta.hmpah.dto.requestDto.PasswordRequest;
import com.sparta.hmpah.dto.responseDto.PasswordResponse;
import com.sparta.hmpah.entity.User;

public interface PasswordService {

    /**
     * 비밀번호 변경
     * @param user 비밀번호 변경 요청자
     * @param passwordRequest 비밀번호 변경에 필요한 요청 정보 데이터
     * @return 비밀번호 변경 정보
     */
    PasswordResponse updatePassword(User user, PasswordRequest passwordRequest);

}
