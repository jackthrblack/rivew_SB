package com.example.rivew.repository;

import com.example.rivew.entity.KakaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;

public interface KakaoRepository extends JpaRepository<KakaoEntity, Long> {
   //  KakaoEntity findkako(HashMap<String, Object> userInfo);

}
