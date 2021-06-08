package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.DatabaseInfoDTO;
import com.hoc.dto.ServerInfoDTO;

public interface IServerInfoService {
	ServerInfoDTO save(ServerInfoDTO serverInfoDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long id);
	List<ServerInfoDTO> findAll(Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem();
	ServerInfoDTO getById(long id);
}
