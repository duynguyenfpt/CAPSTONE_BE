package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.SchemaChangeHistoryEntity;

public interface SchemaChangeHistoryRepository extends JpaRepository<SchemaChangeHistoryEntity, Long>{

}
