package com.web_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.ERole;
import com.web_service.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(ERole name);
}
