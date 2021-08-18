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
		rightEntity.setCode("V01");
		rightEntity.setRightName("View database");
		
		rightDTO = new RightDTO();
		rightDTO.setCode("V01");
		rightDTO.setRightName("View database");
	}
	
	@Test
	public void testToDTO() {
		
		RightDTO rightDTOResult = rightConverter.toDTO(rightEntity);
		
		assertTrue(rightDTOResult.getId() == (long)1 
				&& rightDTOResult.getCode().equals("V01")
				&& rightDTOResult.getRightName().equals("View database"));
	}
	
	@Test
	public void testToEntity() {
		
		RightEntity rightEntityResult = rightConverter.toEntity(rightDTO);
		
		assertTrue(rightEntityResult.getCode().equals("V01")
				&& rightEntityResult.getRightName().equals("View database"));
	}
	
	@Test
	public void updateEntity() {
		
		RightEntity rightEntityResult = rightConverter.toEntity(rightDTO, rightEntity);
		
		assertTrue(rightEntityResult.getCode().equals("V01")
				&& rightEntityResult.getRightName().equals("View database"));
	}
}
