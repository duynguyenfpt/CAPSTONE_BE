package com.hoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

}
