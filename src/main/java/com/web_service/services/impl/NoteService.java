package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.web_service.converter.NoteConvertor;
import com.web_service.dto.NoteDTO;
import com.web_service.entity.mongo.NoteEntity;
import com.web_service.repository.NoteRepository;
import com.web_service.services.INoteService;

@Service
public class NoteService implements INoteService{
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private NoteConvertor noteConvertor;

	@Override
	public NoteDTO save(NoteDTO noteDTO) {
		NoteEntity noteEntity = new NoteEntity();
		if (noteDTO.getId() != null) {
			//update note for request
			NoteEntity oldNoteEntity = noteRepository.findById(noteDTO.getId());
			noteEntity = noteConvertor.toEntity(noteDTO, oldNoteEntity);
		} else {
			//create note for request
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			noteEntity = noteConvertor.toEntity(noteDTO);
			noteEntity.setCreatedBy(auth.getName());
			noteEntity.setCreatedAt(new Date());
		}

		noteEntity = noteRepository.save(noteEntity);
		return noteConvertor.toDTO(noteEntity);
	}

	@Override
	public List<NoteDTO> findAll(Pageable pageable) {
		//get all note
		List<NoteDTO> results = new ArrayList<>();
		List<NoteEntity> entities = noteRepository.findAll(pageable).getContent();
		for (NoteEntity item: entities) {
			NoteDTO noteDTO = noteConvertor.toDTO(item);
			results.add(noteDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) noteRepository.count();
	}

	@Override
	public NoteDTO getById(ObjectId id) {
		//get note by id
		NoteEntity noteEntity = noteRepository.findById(id);
		NoteDTO noteDTO = noteConvertor.toDTO(noteEntity);
		return noteDTO;
	}

	@Override
	public void delete(ObjectId id) {
		noteRepository.delete(id);
		
	}

	@Override
	public List<NoteDTO> findAllByRequestId(long requestId, Pageable pageable) {
		//get note by request
		List<NoteDTO> results = new ArrayList<>();
		List<NoteEntity> entities = noteRepository.findByRequestId(requestId, pageable).getContent();
		for (NoteEntity item: entities) {
			NoteDTO noteDTO = noteConvertor.toDTO(item);
			results.add(noteDTO);
		}
		return results;
	}

	@Override
	public int totalItemByRequestId(long requestId) {
		return noteRepository.getAllNoteByRequestId(requestId).size();
	}

	
}
