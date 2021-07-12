package com.web_service.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import com.web_service.dto.NoteDTO;

public interface INoteService {
	NoteDTO save(NoteDTO jobDTO);
	List<NoteDTO> findAll(Pageable pageable);
	int totalItem();
	NoteDTO getById(ObjectId id);
	void delete(ObjectId id);
	List<NoteDTO> findAllByRequestId(long requestId, Pageable pageable);
	int totalItemByRequestId(long requestId);
}
