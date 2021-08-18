package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.RequestConvertor;
import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.RequestDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.ServerInfoEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestRequestConverter {
	private RequestEntity requestEntity;
	
	private RequestDTO requestDTO;
	
	@InjectMocks
    private static RequestConvertor requestConvertor = new RequestConvertor();
	
	@Before
	public void setUp() {
		requestEntity = new RequestEntity();
		Long id = new Long(1);
		
		requestEntity.setId(id);
		requestEntity.setRequestType("SyncTable");
		requestEntity.setStatus("pending");
		
		requestDTO = new RequestDTO();
		requestDTO.setRequestType("SyncTable");
		requestDTO.setStatus("pending");
	}
	
	@Test
	public void testToDTO() {
		
		RequestDTO requestDTOResult = requestConvertor.toDTO(requestEntity);
		
		assertTrue(requestDTOResult.getId() == (long)1 
				&& requestDTOResult.getRequestType().equals("SyncTable")
				&& requestDTOResult.getStatus().equals("pending"));
	}
	
	@Test
	public void testToEntity() {
		
		RequestEntity requestEntityResult = requestConvertor.toEntity(requestDTO);
		
		assertTrue(requestEntityResult.getRequestType().equals("SyncTable")
				&& requestEntityResult.getStatus().equals("pending"));
	}
	
	@Test
	public void updateEntity() {
		
		RequestEntity requestEntityResult = requestConvertor.toEntity(requestDTO, requestEntity);
		
		assertTrue(requestEntityResult.getRequestType().equals("SyncTable")
				&& requestEntityResult.getStatus().equals("pending"));
	}
}
