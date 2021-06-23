package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.AddColumnTableRequestEntity;

public interface AddColumnTableRequestRepository extends JpaRepository<AddColumnTableRequestEntity, Long> {

}
