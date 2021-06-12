package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.ServerInfoEntity;

public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Long> {

}
