package com.web_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.MergeRequestEntity;
import com.web_service.entity.SyncTableRequestEntity;

public interface MergeRequestRepository extends JpaRepository<MergeRequestEntity, Long> {
	MergeRequestEntity findByRequestId(Long requestId);
}
