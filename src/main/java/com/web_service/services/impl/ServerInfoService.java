package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.ServerInfoRepository;
import com.web_service.services.IServerInfoService;

@Service
public class ServerInfoService implements IServerInfoService {
	@Autowired
	private ServerInfoRepository serverInfoRepository;
	
	@Autowired
	private ServerInfoConverter serverInfoConverter;

	@Override
	public ServerInfoDTO save(ServerInfoDTO serverInfoDTO ) {
		ServerInfoEntity serverInfoEntity = new ServerInfoEntity();
		if (serverInfoDTO.getId() != null) {
			//update server infor
			ServerInfoEntity oldServerInfoEntity = serverInfoRepository.findOne(serverInfoDTO.getId());
			serverInfoEntity = serverInfoConverter.toEntity(serverInfoDTO, oldServerInfoEntity);
		} else {
			//create server infor
			serverInfoEntity = serverInfoConverter.toEntity(serverInfoDTO);
		}

		serverInfoEntity = serverInfoRepository.save(serverInfoEntity);
		return serverInfoConverter.toDTO(serverInfoEntity);
	}

	@Override
	public void delete(long id) {
		serverInfoRepository.delete(id);
	}

	@Override
	public List<ServerInfoDTO> findAll(String keyword, Pageable pageable) {
		if(keyword == null) keyword = "";
		
		//get all server infor
		List<ServerInfoDTO> results = new ArrayList<>();
		List<ServerInfoEntity> entities = serverInfoRepository.getAllServerInfo(keyword, pageable).getContent();
		for (ServerInfoEntity item: entities) {
			ServerInfoDTO serverInfoDTO = serverInfoConverter.toDTO(item);
			results.add(serverInfoDTO);
		}
		return results;
	}

	@Override
	public int totalItem(String keyword) {
		return (int) serverInfoRepository.countServerInfo(keyword);
	}

	@Override
	public ServerInfoDTO getById(long id) {
		//get server infor by id
		ServerInfoEntity serverInfoEntity = serverInfoRepository.findOne(id);
		ServerInfoDTO serverInfoDTO = serverInfoConverter.toDTO(serverInfoEntity);
		return serverInfoDTO;
	}
}
