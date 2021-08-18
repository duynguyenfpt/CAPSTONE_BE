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
import com.web_service.dto.ETLRequestDTO;
import com.web_service.entity.ETLEntity;
import com.web_service.entity.JobEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.repository.ETLRequestRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.impl.ETLService;

@RunWith(MockitoJUnitRunner.class)
public class ETLServiceTest {
	@InjectMocks
    private static ETLService etlService = new ETLService();
		
	@Mock
    private static ETLRequestConverter etlRequestConverter;
	
	@Mock
    private static ETLRequestRepository etlRequestRepository;
	
	@Mock
    private static RequestRepository requestRepository;
	
	@Mock
    private static JobRepository jobRepository;
	
	private ETLEntity etlEntity;
	
	private ETLRequestDTO etlRequestDTO;
	
	private RequestEntity requestEntity;
	
	private JobEntity jobEntity;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		etlEntity = new ETLEntity();
		etlEntity.setId(id);
		etlEntity.setQuery("select * from tables");
		etlEntity.setQueryType("sql");
		etlEntity.setStatus("pending");
		etlEntity.setTotalRows(1000);
		
		etlRequestDTO = new ETLRequestDTO();
		etlRequestDTO.setQuery("select * from tables");
		etlRequestDTO.setQueryType("sql");
		etlRequestDTO.setStatus("pending");
		
		requestEntity = new RequestEntity();
		requestEntity.setId(1L);
		requestEntity.setRequestType("SyncTable");
		requestEntity.setApprovedBy("LongVT");
		
		jobEntity = new JobEntity();
		jobEntity.setId(1L);
		jobEntity.setActive(false);
		jobEntity.setStatus("pending");
    }
	
	@Test
	public void getETLRequest() {
		Long id = new Long(1);
		
		etlRequestDTO.setId(id);
		Mockito.when(etlRequestRepository.findOne(id)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestDTO);
		
		ETLRequestDTO result = etlService.getById(id);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void totalItem() {		
		Mockito.when(etlRequestRepository.count()).thenReturn(1L);
				
		int totalItem = etlService.totalItem();
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void createETLRequest() {
		ETLRequestDTO etlRequestResult = new ETLRequestDTO();
		etlRequestResult.setId(1L);
		etlRequestResult.setQuery("select * from tables");
		etlRequestResult.setQueryType("sql");
		etlRequestResult.setStatus("pending");
		
		Mockito.when(requestRepository.save(requestEntity)).thenReturn(requestEntity);
		
		Mockito.when(jobRepository.save(jobEntity)).thenReturn(jobEntity);
		
		Mockito.when(etlRequestConverter.toEntity(etlRequestDTO)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestRepository.save(etlEntity)).thenReturn(etlEntity);
		
		Mockito.when(etlRequestConverter.toDTO(etlEntity)).thenReturn(etlRequestResult);
				
		ETLRequestDTO result = etlService.save(etlRequestDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void updateETLRequest() {
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
				
		etlService.delete(id);
		
		verify(etlRequestRepository, times(1)).delete(id);
	}
	
	@Test
	public void findAllETLRequest() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<ETLEntity> etlRequestPage = new PageImpl<>(Collections.singletonList(etlEntity));
        Mockito.when(etlRequestRepository.findAll(pageable)).thenReturn(etlRequestPage);
        Page<ETLEntity> etlEntities = etlRequestRepository.findAll(pageable);
        assertEquals(etlEntities.getNumberOfElements(), 1);
	}
}
