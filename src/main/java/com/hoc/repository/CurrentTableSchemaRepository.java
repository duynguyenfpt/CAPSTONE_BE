package com.hoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.CurrentTableSchemaEntity;

public interface CurrentTableSchemaRepository extends JpaRepository<CurrentTableSchemaEntity, Long> {

}
