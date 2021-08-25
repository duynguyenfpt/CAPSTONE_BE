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

import com.web_service.converter.ETLRequestConverter;
import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.ETLRequestDTO;
import com.web_service.entity.ETLEntity;
import com.web_service.repository.ETLRequestRepository;
import com.web_service.services.impl.ETLService;

@RunWith(MockitoJUnitRunner.class)
public class ETLRequestServiceTest {
	@InjectMocks
    private static ETLService etlService = new ETLService();
		
	@Mock
    private static ETLRequestConverter etlRequestConverter;
	
	@Mock
    private static ETLRequestRepository etlRequestRepository;
	
	private ETLEntity etlEntity;
	
	private ETLRequestDTO etlRequestDTO;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		etlEntity = new ETLEntity();
		etlEntity.setId(id);
		etlEntity.setQuery("select * from table;");
		
		etlRequestDTO = new ETLRequestDTO();
		etlRequestDTO.setQuery("select * from table;");
    }
	
	@Test
	public void getServerInfoById() {
		Long id = new Long(1);
		
		etlRequestDTO.setId(id);
		Mockito.when(etlRequestRepository.findOne(id)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestDTO);
		
		ETLRequestDTO result = etlService.getById(id);
		
		assertTrue(result.getId() == 1 && result.getQuery().equals("select * from table;"));
	}
	
	@Test
	public void getTableByIdWithBoundary() {
		Long id = new Long(9223372036854775807L);
		
		etlRequestDTO.setId(id);
		Mockito.when(etlRequestRepository.findOne(id)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestDTO);
		
		ETLRequestDTO result = etlService.getById(id);
		
		assertTrue(result.getId() == 9223372036854775807L);
	}
	
	@Test
	public void getRequestByIdWithZero() {
		Long id = new Long(0);
		
		etlRequestDTO.setId(id);
		Mockito.when(etlRequestRepository.findOne(id)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestDTO);
		
		ETLRequestDTO result = etlService.getById(id);
		
		assertTrue(result.getId() == 0);
	}
	
	@Test
	public void totalItem() {		
		Mockito.when(etlRequestRepository.count()).thenReturn(1L);
				
		int totalItem = etlService.totalItem();
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void updateETLRequest() {
		etlRequestDTO = new ETLRequestDTO();
		etlRequestDTO.setId(1L);
		etlRequestDTO.setQueryType("sql");
		
		Mockito.when(etlRequestConverter.toEntity(etlRequestDTO, etlEntity)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestRepository.findOne(1L)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestRepository.save(etlEntity)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestDTO);
				
		ETLRequestDTO result = etlService.save(etlRequestDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void updateETLRequestWithoutQuery() {
		etlRequestDTO.setId(1L);
		Mockito.when(etlRequestConverter.toEntity(etlRequestDTO, etlEntity)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestRepository.findOne(1L)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestRepository.save(etlEntity)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestDTO);
				
		ETLRequestDTO result = etlService.save(etlRequestDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void deleteETLRequest() {
		Long id = new Long(1);
				
		etlRequestRepository.delete(id);
		
		verify(etlRequestRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteETLRequestInfoWithZero() {
		Long id = new Long(0);
				
		etlService.delete(id);
		
		verify(etlRequestRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteServerInfoWithBoundaryLong() {
		Long id = new Long(9223372036854775807L);
				
		etlService.delete(id);
		
		verify(etlRequestRepository, times(1)).delete(id);
	}
}
