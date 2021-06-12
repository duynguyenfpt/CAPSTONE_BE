package com.web_service.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

	Page<TableEntity> findByDatabaseInfo(DatabaseInfoEntity databaseInfoEntity, Pageable pageable);
}
