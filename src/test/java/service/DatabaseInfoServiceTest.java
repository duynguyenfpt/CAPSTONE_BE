package service;

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
import org.springframework.data.domain.*;

import com.web_service.converter.DatabaseInfoConverter;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.DatabaseInfoRepository;
import com.web_service.repository.ServerInfoRepository;
import com.web_service.services.impl.DatabaseInfoService;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseInfoServiceTest{
	@InjectMocks
    private static DatabaseInfoService databaseInfoService = new DatabaseInfoService();
		
	@Mock
    private static DatabaseInfoConverter databaseInfoConverter;
	
	@Mock
    private static DatabaseInfoRepository databaseInfoRepository;
	
	@Mock
    private static ServerInfoRepository serverInfoRepository;
	
	private ServerInfoEntity serverInfoEntity;
	
	private DatabaseInfoEntity databaseInfoEntity;
	
	private DatabaseInfoDTO databaseInfoDTO;
	
	private ServerInfoDTO serverInfoDTO;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		serverInfoEntity = new ServerInfoEntity();
		serverInfoEntity.setId(id);
		serverInfoEntity.setServerDomain("10.8.0.1");
		serverInfoEntity.setServerHost("localhost");
		
		serverInfoDTO = new ServerInfoDTO();
		serverInfoDTO.setId(id);
		serverInfoDTO.setServerDomain("10.8.0.1");
		serverInfoDTO.setServerHost("localhost");

		databaseInfoEntity = new DatabaseInfoEntity();
		databaseInfoEntity.setId(id);
		databaseInfoEntity.setPort("8080");
		databaseInfoEntity.setUsername("root");
		databaseInfoEntity.setPassword("123456");
		databaseInfoEntity.setDatabaseName("webservice");
		databaseInfoEntity.setDatabaseType("postgresql");
		databaseInfoEntity.setAlias("a");
		databaseInfoEntity.setServerInfo(serverInfoEntity);

		databaseInfoDTO = new DatabaseInfoDTO();
		databaseInfoDTO.setPort("8080");
		databaseInfoDTO.setUsername("root");
		databaseInfoDTO.setPassword("123456");
		databaseInfoDTO.setDatabaseName("webservice");
		databaseInfoDTO.setDatabaseType("postgresql");
		databaseInfoDTO.setAlias("a");
		databaseInfoDTO.setHost("localhost");
		databaseInfoDTO.setServerInforId(id);
    }
	
	@Test
	public void testGetDatabaseById() {
		Long id = new Long(1);
		databaseInfoDTO.setId(id);
		Mockito.when(databaseInfoRepository.findOne(id)).thenReturn(databaseInfoEntity);
		
		Mockito.when(databaseInfoConverter.toDTO(databaseInfoEntity)).thenReturn(databaseInfoDTO);
		
		DatabaseInfoDTO databaseInfoResult = databaseInfoService.getById(id);
		
		assertTrue(databaseInfoResult.getId() == 1);
	}
	
	@Test
	public void testTotalItemWithoutKeyword() {		
		Mockito.when(databaseInfoRepository.count()).thenReturn(1L);
				
		long totalItem = databaseInfoService.totalItem("");
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void testTotalItemWithKeyword() {		
		Mockito.when(databaseInfoRepository.search("8080")).thenReturn(1);
				
		long totalItem = databaseInfoService.totalItem("8080");
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void testCreateDatabaseMysql() {
		Long id = new Long(1);
		databaseInfoDTO.setDatabaseType("mysql");
		
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
				
		DatabaseInfoDTO databaseInfoResult = databaseInfoService.save(databaseInfoDTO);
		
		assertNull(databaseInfoResult);
	}
	
	@Test
	public void testCreateDatabasePostgresql() {
		Long id = new Long(1);
		databaseInfoDTO.setDatabaseType("postgresql");
		
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
				
		DatabaseInfoDTO databaseInfoResult = databaseInfoService.save(databaseInfoDTO);
		
		assertNull(databaseInfoResult);
	}
	
	@Test
	public void testCreateDatabaseOracle() {
		Long id = new Long(1);
		databaseInfoDTO.setDatabaseType("oracle");
		
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
				
		DatabaseInfoDTO databaseInfoResult = databaseInfoService.save(databaseInfoDTO);
		
		assertNull(databaseInfoResult);
	}
	
	@Test
	public void deleteDatabase() {
		Long id = new Long(1);
				
		databaseInfoService.delete(id);
		
		verify(databaseInfoRepository, times(1)).delete(id);
	}
	
	@Test
	public void getDatabaseWithoutKeyword() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<DatabaseInfoEntity> databasePage = new PageImpl<>(Collections.singletonList(databaseInfoEntity));
        Mockito.when(databaseInfoRepository.findAll(pageable)).thenReturn(databasePage);
        Page<DatabaseInfoEntity> databases = databaseInfoRepository.findAll(pageable);
        assertEquals(databases.getNumberOfElements(), 1);
	}
	
	@Test
	public void getDatabaseWithKeyword() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<DatabaseInfoEntity> databasePage = new PageImpl<>(Collections.singletonList(databaseInfoEntity));
        Mockito.when(databaseInfoRepository.search("abc" ,pageable)).thenReturn(databasePage);
        Page<DatabaseInfoEntity> databases = databaseInfoRepository.search("abc", pageable);
        assertEquals(databases.getNumberOfElements(), 1);
	}
	
	@Test
	public void trackingConnectionWithMysql() {
		databaseInfoDTO.setDatabaseType("mysql");
		Long id = new Long(1);
		
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
		
	    boolean trackingConnection = databaseInfoService.trackingConnection(databaseInfoDTO);
	    assertEquals(trackingConnection, false);
	}
	
	@Test
	public void trackingConnectionWithPostgresql() {
		databaseInfoDTO.setDatabaseType("postgresql");
		Long id = new Long(1);
		
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
		
	    boolean trackingConnection = databaseInfoService.trackingConnection(databaseInfoDTO);
	    assertEquals(trackingConnection, false);
	}
	
	@Test
	public void trackingConnectionWithOracle() {
		databaseInfoDTO.setDatabaseType("oracle");
		Long id = new Long(1);
		
		Mockito.when(serverInfoRepository.findOne(id)).thenReturn(serverInfoEntity);
		
	    boolean trackingConnection = databaseInfoService.trackingConnection(databaseInfoDTO);
	    assertEquals(trackingConnection, false);
	}
}
