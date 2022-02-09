package com.example.rivew.service;

import java.util.HashMap;

public interface KakaoService {
    void kakaoLogout(String accessToken);

    HashMap<String, Object> getUserInfo(String accessToken);

    String getAccessToken(String code);
}
