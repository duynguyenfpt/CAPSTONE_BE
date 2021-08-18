package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.MergeRequestDTO;

public interface IMergeRequestService {
	MergeRequestDTO save(MergeRequestDTO mergeRequestDTO);
	void delete(long id);
	List<MergeRequestDTO> findAll(Pageable pageable);
	int totalItem();
	MergeRequestDTO getByRequestId(long requestId);
	MergeRequestDTO update(MergeRequestDTO mergeRequestDTO);
}
