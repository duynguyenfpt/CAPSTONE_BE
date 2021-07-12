package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.NoteDTO;
import com.web_service.entity.mongo.NoteEntity;

@Component
public class NoteConvertor {
	public NoteEntity toEntity(NoteDTO dto) {
		NoteEntity entity = new NoteEntity();
		entity.setRequestId(dto.getRequestId());
		entity.setContent(dto.getContent());
		
		return entity;
	}
	
	public NoteDTO toDTO(NoteEntity entity) {
		NoteDTO dto = new NoteDTO();

		if(entity.get_id() != null) {
			dto.setId(entity.get_id());
		}
	
		dto.setRequestId(entity.getRequestId());
		dto.setContent(entity.getContent());
		
		return dto;
	}
	
	public NoteEntity toEntity(NoteDTO dto, NoteEntity entity) {
		entity.setRequestId(dto.getRequestId());
		entity.setContent(dto.getContent());
		
		return entity;
	}
}
