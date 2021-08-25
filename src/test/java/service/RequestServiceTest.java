package service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.web_service.converter.RequestConvertor;
import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.RequestDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.RequestRepository;
import com.web_service.repository.ServerInfoRepository;
import com.web_service.services.impl.RequestService;
import com.web_service.services.impl.ServerInfoService;

@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTest{
	@InjectMocks
    private static RequestService requestService = new RequestService();
		
	@Mock
    private static RequestConvertor requestConvertor;
	
	@Mock
    private static RequestRepository requestRepository;
	
	private RequestEntity requestEntity;
	
	private RequestDTO requestDTO;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		requestEntity = new RequestEntity();
		requestEntity.setId(id);
		requestEntity.setRequestType("SyncTable");
		
		requestDTO = new RequestDTO();
		requestDTO.setRequestType("SyncTable");
    }
	
	@Test
	public void getRequestById() {
		Long id = new Long(1);
		
		requestDTO.setId(id);
		Mockito.when(requestRepository.findOne(id)).thenReturn(requestEntity);
		
		Mockito.when(requestConvertor.toDTO(requestEntity)).thenReturn(requestDTO);
		
		RequestDTO result = requestService.getById(id);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void getRequestByIdWithBoundary() {
		Long id = new Long(9223372036854775807L);
		
		requestDTO.setId(id);
		Mockito.when(requestRepository.findOne(id)).thenReturn(requestEntity);
		
		Mockito.when(requestConvertor.toDTO(requestEntity)).thenReturn(requestDTO);
		
		RequestDTO result = requestService.getById(id);
		
		assertTrue(result.getId() == 9223372036854775807L);
	}
	
	@Test
	public void getRequestByIdWithZero() {
		Long id = new Long(0);
		
		requestDTO.setId(id);
		Mockito.when(requestRepository.findOne(id)).thenReturn(requestEntity);
		
		Mockito.when(requestConvertor.toDTO(requestEntity)).thenReturn(requestDTO);
		
		RequestDTO result = requestService.getById(id);
		
		assertTrue(result.getId() == 0);
	}

	@Test
	public void deleteServerInfo() {
		Long id = new Long(1);
				
		requestService.delete(id);
		
		verify(requestRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteServerInfoWithZero() {
		Long id = new Long(0);
				
		requestService.delete(id);
		
		verify(requestRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteServerInfoWithBoundaryLong() {
		Long id = new Long(9223372036854775807L);
				
		requestService.delete(id);
		
		verify(requestRepository, times(1)).delete(id);
	}
}
