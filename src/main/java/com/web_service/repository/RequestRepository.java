package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

}
