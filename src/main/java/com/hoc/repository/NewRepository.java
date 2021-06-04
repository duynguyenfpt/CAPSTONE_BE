package com.hoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long>{
	
}
