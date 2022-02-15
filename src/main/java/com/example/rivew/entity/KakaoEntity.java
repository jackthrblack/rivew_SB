package com.example.rivew.entity;

import com.example.rivew.dto.KakaoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Getter
@Setter
@Table(name="kakao")
public class KakaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakaoId")
    private Long id;

    @Column
    private String kakaName;

    @Column
    private String kakaEmail;


    public static KakaoEntity saveKakao(HashMap<String, Object> userInfo) {

        KakaoEntity kakaoEntity = new KakaoEntity();
        System.out.println(userInfo.get("email").toString());
        kakaoEntity.setKakaEmail((String) userInfo.get("email"));
        kakaoEntity.setKakaName((String) userInfo.get("nickname"));
        return kakaoEntity;
    }
}
