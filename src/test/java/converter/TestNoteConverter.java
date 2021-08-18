package converter;

import static org.junit.Assert.assertTrue;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.NoteConvertor;
import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.NoteDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.entity.mongo.NoteEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestNoteConverter {
	
	private NoteEntity noteEntity;
	
	private NoteDTO noteDTO;
	
	private ObjectId id;
	
	@InjectMocks
    private static NoteConvertor noteConvertor = new NoteConvertor();
	
	@Before
	public void setUp() {
		noteEntity = new NoteEntity();
		id = new ObjectId();
		
		noteEntity.set_id(id);
		noteEntity.setRequestId((long)1);
		noteEntity.setContent("approved");
		
		noteDTO = new NoteDTO();
		noteDTO.setRequestId((long)1);
		noteDTO.setContent("approved");
	}
	
	@Test
	public void testToDTO() {
		
		NoteDTO noteDTOResult = noteConvertor.toDTO(noteEntity);
		
		assertTrue(noteDTOResult.getId().equals(id)
				&& noteDTOResult.getRequestId() == 1
				&& noteDTOResult.getContent().equals("approved"));
	}
	
	@Test
	public void testToEntity() {
		
		NoteEntity noteEntityResult = noteConvertor.toEntity(noteDTO);
		
		assertTrue(noteEntityResult.getRequestId() == 1
				&& noteEntityResult.getContent().equals("approved"));
	}
	
	@Test
	public void updateEntity() {
		
		NoteEntity noteEntityResult = noteConvertor.toEntity(noteDTO, noteEntity);
		
		assertTrue(noteEntityResult.getRequestId() == 1
				&& noteEntityResult.getContent().equals("approved"));	
	}
}
