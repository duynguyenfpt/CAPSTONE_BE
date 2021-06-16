package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.AddColumnTableRequestEntity;
import com.web_service.entity.CurrentTableSchemaEntity;

public interface AddColumnTableRequestRepository extends JpaRepository<AddColumnTableRequestEntity, Long> {

}
