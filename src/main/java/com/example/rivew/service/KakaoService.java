package com.example.rivew.service;

import com.example.rivew.dto.KakaoDTO;
import com.example.rivew.dto.KakaoDetailDTO;

import java.util.HashMap;
import java.util.List;

public interface KakaoService {
    void kakaoLogout(String accessToken);

    HashMap<String, Object> getUserInfo(String access_Token, KakaoDTO kakaoDTO);

    String getAccessToken(String code);

    List<KakaoDetailDTO> findAll();
}
