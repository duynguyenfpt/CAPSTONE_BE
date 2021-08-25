package service;

import static org.junit.Assert.assertEquals;
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

import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.TableDTO;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.ServerInfoRepository;
import com.web_service.services.impl.ServerInfoService;

@RunWith(MockitoJUnitRunner.class)
public class ServerInfoServiceTest{
	@InjectMocks
    private static ServerInfoService serverInfoService = new ServerInfoService();
		
	@Mock
    private static ServerInfoConverter serverInfoConverter;
	
	@Mock
    private static ServerInfoRepository serverInfoRepository;
	
	private ServerInfoEntity serverInfoEntity;
	
	private ServerInfoDTO serverInfoDTO;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		serverInfoEntity = new ServerInfoEntity();
		serverInfoEntity.setId(id);
		serverInfoEntity.setServerDomain("10.8.0.1");
		serverInfoEntity.setServerHost("localhost");
		
		serverInfoDTO = new ServerInfoDTO();
		serverInfoDTO.setServerDomain("10.8.0.1");
		serverInfoDTO.setServerHost("localhost");
    }
	
	@Test
	public void getServerInfoById() {
		Long id = new Long(1);
		
		serverInfoDTO.setId(id);
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoDTO);
		
		ServerInfoDTO result = serverInfoService.getById(id);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void getServerInforByIdWithBoundary() {
		Long id = new Long(9223372036854775807L);
		
		serverInfoDTO.setId(id);
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoDTO);
		
		ServerInfoDTO result = serverInfoService.getById(id);
		
		assertTrue(result.getId() == 9223372036854775807L);
	}
	
	@Test
	public void getServerInforByIdWithZero() {
		Long id = new Long(0);
		
		serverInfoDTO.setId(id);
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoDTO);
		
		ServerInfoDTO result = serverInfoService.getById(id);
		
		assertTrue(result.getId() == 0);
	}
	
	@Test
	public void totalItem() {		
		Mockito.when(serverInfoRepository.countServerInfo("")).thenReturn(1);
				
		int totalItem = serverInfoService.totalItem("");
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void createServerInfo() {
		ServerInfoDTO serverInfo = new ServerInfoDTO();
		serverInfo.setId(1L);
		serverInfo.setServerHost("localhost");
		serverInfo.setServerDomain("localhost");
		
		Mockito.when(serverInfoConverter.toEntity(serverInfoDTO)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfo);
		
		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
				
		ServerInfoDTO result = serverInfoService.save(serverInfoDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void createServerInfoWithourHost() {
		ServerInfoDTO serverInfo = new ServerInfoDTO();
		serverInfo.setId(1L);
		serverInfo.setServerHost("localhost");
		
		Mockito.when(serverInfoConverter.toEntity(serverInfoDTO)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfo);
		
		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
				
		ServerInfoDTO result = serverInfoService.save(serverInfoDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void createServerInfoWithoutDomain() {
		ServerInfoDTO serverInfo = new ServerInfoDTO();
		serverInfo.setId(1L);
		serverInfo.setServerDomain("localhost");
		
		Mockito.when(serverInfoConverter.toEntity(serverInfoDTO)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfo);
		
		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
				
		ServerInfoDTO result = serverInfoService.save(serverInfoDTO);
		
		assertEquals(result.getId(), 1);
	}

	
	@Test
	public void updateServerInfo() {
		serverInfoDTO.setId(1L);
		Mockito.when(serverInfoConverter.toEntity(serverInfoDTO, serverInfoEntity)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoRepository.findOne(1L)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoDTO);
				
		ServerInfoDTO result = serverInfoService.save(serverInfoDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void updateServerInfoWithoutHost() {
		ServerInfoDTO serverInfoTest = new ServerInfoDTO();
		serverInfoTest.setId(1L);
		serverInfoTest.setServerHost("localhost");
		Mockito.when(serverInfoConverter.toEntity(serverInfoTest, serverInfoEntity)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoRepository.findOne(1L)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoTest);
				
		ServerInfoDTO result = serverInfoService.save(serverInfoTest);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void updateServerInfoWithoutDomain() {
		ServerInfoDTO serverInfoTest = new ServerInfoDTO();
		serverInfoTest.setId(1L);
		serverInfoTest.setServerHost("localhost");
		Mockito.when(serverInfoConverter.toEntity(serverInfoTest, serverInfoEntity)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoRepository.findOne(1L)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoRepository.save(serverInfoEntity)).thenReturn(serverInfoEntity);
		
		Mockito.when(serverInfoConverter.toDTO(serverInfoEntity)).thenReturn(serverInfoTest);
				
		ServerInfoDTO result = serverInfoService.save(serverInfoTest);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void deleteServerInfo() {
		Long id = new Long(1);
				
		serverInfoService.delete(id);
		
		verify(serverInfoRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteServerInfoWithZero() {
		Long id = new Long(0);
				
		serverInfoService.delete(id);
		
		verify(serverInfoRepository, times(1)).delete(id);
	}
	
	@Test
	public void deleteServerInfoWithBoundaryLong() {
		Long id = new Long(9223372036854775807L);
				
		serverInfoService.delete(id);
		
		verify(serverInfoRepository, times(1)).delete(id);
	}
	
	@Test
	public void findAllServerInfo() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<ServerInfoEntity> serverPage = new PageImpl<>(Collections.singletonList(serverInfoEntity));
        Mockito.when(serverInfoRepository.findAll(pageable)).thenReturn(serverPage);
        Page<ServerInfoEntity> servers = serverInfoRepository.findAll(pageable);
        assertEquals(servers.getNumberOfElements(), 1);
	}
}
