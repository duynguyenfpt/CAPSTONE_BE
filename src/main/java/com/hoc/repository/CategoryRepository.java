package com.hoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
}
