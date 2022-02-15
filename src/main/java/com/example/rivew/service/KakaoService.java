package com.example.rivew.service;

public interface KakaoService {
    void kakaoLogout(String accessToken);

    Long getUserInfo(String access_Token);

    String getAccessToken(String code);
}
