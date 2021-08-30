package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.ServerInfoDTO;

public interface IServerInfoService {
	ServerInfoDTO save(ServerInfoDTO serverInfoDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long id);
	List<ServerInfoDTO> findAll(String keyword, Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem(String keyword);
	ServerInfoDTO getById(long id);
	boolean isDuplicateHostAndDomain(ServerInfoDTO serverInfoDTO);
}
