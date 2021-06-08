package com.hoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.ServerInfoEntity;

public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Long> {

}
