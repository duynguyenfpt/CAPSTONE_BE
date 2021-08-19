package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.RightConverter;
import com.web_service.dto.RightDTO;
import com.web_service.entity.RightEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestRightConverter {
	private RightEntity rightEntity;
	
	private RightDTO rightDTO;
	
	@InjectMocks
    private static RightConverter rightConverter = new RightConverter();
	
	@Before
	public void setUp() {
		rightEntity = new RightEntity();
		Long id = new Long(1);
		
		rightEntity.setId(id);
		rightEntity.setPath("database_infors");
		rightEntity.setMethod("POST");
		
		rightDTO = new RightDTO();
		rightDTO.setPath("database_infors");
		rightDTO.setMethod("POST");
	}
	
	@Test
	public void testToDTO() {
		
		RightDTO rightDTOResult = rightConverter.toDTO(rightEntity);
		
		assertTrue(rightDTOResult.getId() == (long)1 
				&& rightDTOResult.getPath().equals("database_infors")
				&& rightDTOResult.getMethod().equals("POST"));
	}
	
	@Test
	public void testToEntity() {
		
		RightEntity rightEntityResult = rightConverter.toEntity(rightDTO);
		
		assertTrue(rightEntityResult.getPath().equals("database_infors")
				&& rightEntityResult.getMethod().equals("POST"));
	}
	
	@Test
	public void updateEntity() {
		
		RightEntity rightEntityResult = rightConverter.toEntity(rightDTO, rightEntity);
		
		assertTrue(rightEntityResult.getPath().equals("database_infors")
				&& rightEntityResult.getMethod().equals("POST"));
	}
}
