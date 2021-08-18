package service;

import static org.junit.Assert.assertEquals;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.converter.DatabaseInfoConverter;
import com.web_service.converter.TableConverter;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.TableDTO;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.DatabaseInfoRepository;
import com.web_service.repository.ServerInfoRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.impl.DatabaseInfoService;
import com.web_service.services.impl.TableService;

@RunWith(MockitoJUnitRunner.class)
public class TableServiceTest{
	@InjectMocks
    private static TableService tableService = new TableService();
		
	@Mock
    private static TableConverter tableConverter;
	
	@Mock
	private static TableRepository tableRepository;
	
	@Mock
    private static DatabaseInfoRepository databaseInfoRepository;
	
	@Mock
    private static ServerInfoRepository serverInfoRepository;
	
	private ServerInfoEntity serverInfoEntity;
	
	private DatabaseInfoEntity databaseInfoEntity;
	
	private DatabaseInfoDTO databaseInfoDTO;
	
	private ServerInfoDTO serverInfoDTO;
	
	private TableEntity tableEntity;
	
	private TableDTO tableDTO;
		
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
		
		tableEntity = new TableEntity();
		tableEntity.setId(1L);
		tableEntity.setTableName("student");
		
		tableDTO = new TableDTO();
		tableDTO.setTableName("student");
    }
	
	@Test
	public void testGetDatabaseById() {
		tableDTO.setId(1L);
		Mockito.when(tableRepository.findOne(1L)).thenReturn(tableEntity);
		
		Mockito.when(tableConverter.toDTO(tableEntity)).thenReturn(tableDTO);
		
		TableDTO result = tableService.getById(1L);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void totalItem() {		
		Mockito.when(tableRepository.countSearchTotalItem("")).thenReturn(1);
				
		long totalItem = tableService.totalItem("");
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void deleteTable() {				
		tableService.delete(1L);
		
		verify(tableRepository, times(1)).delete(1L);
	}
	
	@Test
	public void createTable() {
		TableDTO tableAfterSave = new TableDTO();
		tableAfterSave.setId(1L);
		tableAfterSave.setTableName("student");
				
		Mockito.when(tableRepository.save(tableEntity)).thenReturn(tableEntity);
		
		Mockito.when(tableConverter.toEntity(tableDTO)).thenReturn(tableEntity);
		
		Mockito.when(tableConverter.toDTO(tableEntity)).thenReturn(tableAfterSave);
		
		TableDTO result = tableService.save(tableDTO);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void updateTable() {
		tableDTO.setId(1L);
		
		TableDTO tableAfterSave = new TableDTO();
		tableAfterSave.setId(1L);
		tableAfterSave.setTableName("student");
				
		Mockito.when(tableRepository.findOne(1L)).thenReturn(tableEntity);
		
		Mockito.when(tableRepository.save(tableEntity)).thenReturn(tableEntity);
		
		Mockito.when(tableConverter.toEntity(tableDTO, tableEntity)).thenReturn(tableEntity);
		
		Mockito.when(tableConverter.toDTO(tableEntity)).thenReturn(tableAfterSave);
		
		TableDTO result = tableService.save(tableDTO);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void findByDatabaseInfoId() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<TableEntity> tablePage = new PageImpl<>(Collections.singletonList(tableEntity));
        Mockito.when(tableRepository.findByDatabaseInfo(databaseInfoEntity, pageable)).thenReturn(tablePage);
        Page<TableEntity> tables = tableRepository.findByDatabaseInfo(databaseInfoEntity, pageable);
        assertEquals(tables.getNumberOfElements(), 1);
	}
	
	@Test
	public void totalItemByDatabaseId() {
		Mockito.when(databaseInfoRepository.findOne(1L)).thenReturn(databaseInfoEntity);
		
		int totalItem = tableService.totalItemByDatabaseId(1L);
		
		assertEquals(totalItem, 0);
	}
}
