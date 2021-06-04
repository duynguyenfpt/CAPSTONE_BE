package com.hoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoc.entity.ERole;
import com.hoc.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(ERole name);
}
