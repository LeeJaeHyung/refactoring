package com.sparta.hmpah.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;


public interface KakaoService {

    /**
     * 카카오 로그인
     * @param code 액세스 토큰 인가코드
     * @return 로그인한 유저정보
     * @throws JsonProcessingException 토큰 변환에 대한 예외 처리
     */
    Map<String, Object> kakaoLogin(String code) throws JsonProcessingException;

}