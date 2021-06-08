package com.hoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.SchemaChangeHistoryEntity;
import com.hoc.entity.TableEntity;

public interface SchemaChangeHistoryRepository extends JpaRepository<SchemaChangeHistoryEntity, Long>{

}
