package com.hoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hoc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByUserName(String username);
	
	Boolean existByUsername(String username);
	
	Boolean existByEmail(String email);
	
}
