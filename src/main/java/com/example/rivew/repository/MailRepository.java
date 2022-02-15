package com.example.rivew.repository;

import com.example.rivew.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<MailEntity, Long> {
}
