package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.web_service.converter.JobConvertor;
import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.JobDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.JobEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.impl.JobService;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {
	@InjectMocks
    private static JobService jobService = new JobService();
		
	@Mock
    private static JobConvertor jobConvertor;
	
	@Mock
    private static JobRepository jobRepository;
	
	@Mock
	private static AccountRepository accountRepository;
	
	@Mock
	private static RequestRepository requestRepository;
	
	private JobEntity jobEntity;
	
	private JobDTO jobDTO;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		jobEntity = new JobEntity();
		jobEntity.setId(id);
		jobEntity.setDescription("job description");
		jobEntity.setStatus("pending");
		
		jobDTO = new JobDTO();
		jobDTO.setStatus("pending");
		jobDTO.setExecutedById(1);
		jobDTO.setRequestId(1);
    }
	
	@Test
	public void getJobById() {
		Long id = new Long(1);
		
		jobDTO.setId(id);
		Mockito.when(jobRepository.findOne(id)).thenReturn(jobEntity);
		
		Mockito.when(jobConvertor.toDTO(jobEntity)).thenReturn(jobDTO);
		
		JobDTO result = jobService.getById(id);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void getServerInforByIdWithBoundary() {
		Long id = new Long(9223372036854775807L);
		
		jobDTO.setId(id);
		Mockito.when(jobRepository.findOne(id)).thenReturn(jobEntity);
		
		Mockito.when(jobConvertor.toDTO(jobEntity)).thenReturn(jobDTO);
		
		JobDTO result = jobService.getById(id);
		
		assertTrue(result.getId() == 9223372036854775807L);
	}
	
	@Test
	public void getJobByIdWithZero() {
		Long id = new Long(0);
		
		jobDTO.setId(id);
		Mockito.when(jobRepository.findOne(id)).thenReturn(jobEntity);
		
		Mockito.when(jobConvertor.toDTO(jobEntity)).thenReturn(jobDTO);
		
		JobDTO result = jobService.getById(id);
		
		assertTrue(result.getId() == 0);
	}
	
	@Test
	public void totalItem() {		
		Mockito.when(jobRepository.countJob("")).thenReturn(1);
				
		int totalItem = jobService.totalItem("");
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void createJob() {
		RequestEntity requestEntity = new RequestEntity();
		requestEntity.setId(1L);
		requestEntity.setRequestType("SyncTable");
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(1L);
		accountEntity.setUsername("longvt");
		accountEntity.setEmail("longvt@gmail.com");
		accountEntity.setRole("admin");
		
		Mockito.when(jobConvertor.toEntity(jobDTO)).thenReturn(jobEntity);
		
		Mockito.when(jobConvertor.toDTO(jobEntity)).thenReturn(jobDTO);
		
		Mockito.when(jobRepository.save(jobEntity)).thenReturn(jobEntity);
		
		Mockito.when(requestRepository.findOne(1L)).thenReturn(requestEntity);

		Mockito.when(accountRepository.findOne(1L)).thenReturn(accountEntity);
				
		JobDTO result = jobService.save(jobDTO);
		
		assertNotNull(result);
	}
	
//	@Test
//	public void updateServerInfo() {
//		serverInfoDTO.setId(1L);
//		Mockito.when(serverInfoConverter.toEntity(serverInfoDTO, serverInfoEntity)).thenReturn(serverInfoEntity);
//		
//		Mockito.when(serverInfoRepository.findOne(1L)).thenReturn(serverInfoEntity);
//		
//		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
//		
//		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoDTO);
//				
//		ServerInfoDTO result = serverInfoService.save(serverInfoDTO);
//		
//		assertEquals(result.getId(), 1);
//	}
	
	@Test
	public void deleteJob() {
		Long id = new Long(1);
				
		jobService.delete(id);
		
		verify(jobRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteJobWithZero() {
		Long id = new Long(0);
				
		jobService.delete(id);
		
		verify(jobRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteJobWithBoundaryLong() {
		Long id = new Long(9223372036854775807L);
				
		jobService.delete(id);
		
		verify(jobRepository, times(1)).delete(id);
	}
	
//	@Test
//	public void findAllServerInfo() {
//		int page = 0;
//		int limit = 1;
//		
//        Pageable pageable = new PageRequest(page, limit);
//        Page<ServerInfoEntity> serverPage = new PageImpl<>(Collections.singletonList(serverInfoEntity));
//        Mockito.when(serverInfoRepository.findAll(pageable)).thenReturn(serverPage);
//        Page<ServerInfoEntity> servers = serverInfoRepository.findAll(pageable);
//        assertEquals(servers.getNumberOfElements(), 1);
//	}
}
