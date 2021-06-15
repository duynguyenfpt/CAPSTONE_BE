package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.SyncTableRequestEntity;

public interface SyncTableRequestRepository extends JpaRepository<SyncTableRequestEntity, Long> {

}
