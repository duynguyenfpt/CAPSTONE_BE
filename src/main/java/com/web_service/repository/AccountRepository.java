package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findByUsername(String username);
}
