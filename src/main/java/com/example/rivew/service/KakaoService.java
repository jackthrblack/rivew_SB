package com.example.rivew.service;

import java.util.HashMap;

public interface KakaoService {
    void kakaoLogout(String accessToken);

    HashMap<String, Object> getUserInfo(String access_Token);

    String getAccessToken(String code);
}
