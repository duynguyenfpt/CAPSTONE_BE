package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.ServerInfoDTO;

public interface IServerInfoService {
	ServerInfoDTO save(ServerInfoDTO serverInfoDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long id);
	List<ServerInfoDTO> findAll(Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem();
	ServerInfoDTO getById(long id);
}
