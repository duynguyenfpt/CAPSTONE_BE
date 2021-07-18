package com.web_service.api;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.JobDTO;
import com.web_service.dto.NoteDTO;
import com.web_service.services.INoteService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class NoteAPI {
	@Autowired
	private INoteService noteService;
	
	@GetMapping(value = "/api/notes")
	public ResponseEntity<ListObjOutput<NoteDTO>> getAllNotes(@RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		
		ListObjOutput<NoteDTO> result = new ListObjOutput<NoteDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(noteService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (noteService.totalItem()) / limit);
		int totalItem = noteService.totalItem();
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<NoteDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/requests/{request_id}/notes")
	public ResponseEntity<ListObjOutput<NoteDTO>> getAllNotesByRequest(@RequestParam("page") int page,
			@RequestParam("limit") int limit,  @PathVariable("request_id") long requestId) {
		
		ListObjOutput<NoteDTO> result = new ListObjOutput<NoteDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit, new Sort(new Order(Direction.DESC, "timestamp")));
			
			
			result.setData(noteService.findAllByRequestId(requestId, pageable));
			int totalPage = (int) Math.ceil((double) (noteService.totalItemByRequestId(requestId)) / limit);
			int totalItem = noteService.totalItemByRequestId(requestId);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<NoteDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ListObjOutput<NoteDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<NoteDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/api/notes/{id}")
	public ResponseEntity<ObjectOuput<NoteDTO>> getNoteId(@PathVariable("id") ObjectId id) {
		
		NoteDTO noteDTO =  noteService.getById(id);
		ObjectOuput<NoteDTO> result = new ObjectOuput<NoteDTO>();
		result.setData(noteDTO);
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<NoteDTO>>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/api/notes/{id}")
	public ResponseEntity<ObjectOuput<NoteDTO>> updateNoteById(@PathVariable("id") ObjectId id, @Valid @RequestBody NoteDTO note) {
		note.setId(id);
		noteService.save(note);
		
		ObjectOuput<NoteDTO> result = new ObjectOuput<NoteDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<NoteDTO>>(result, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/api/notes")
	public ResponseEntity<ObjectOuput<NoteDTO>> createNote(@Valid @RequestBody NoteDTO note) {
		ObjectOuput<NoteDTO> result = new ObjectOuput<NoteDTO>();
		result.setData(noteService.save(note));
		result.setCode("201");
		
		return new ResponseEntity<ObjectOuput<NoteDTO>>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/api/notes/{id}")
    public ResponseEntity<ObjectOuput<NoteDTO>> deleteNote(@PathVariable ObjectId id) {
		noteService.delete(id);
      
      	ObjectOuput<NoteDTO> result = new ObjectOuput<NoteDTO>();
      	result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<NoteDTO>>(result, HttpStatus.OK);
    }
}
