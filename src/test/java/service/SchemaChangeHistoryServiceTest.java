package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import com.web_service.converter.CurrentTableSchemaConverter;
import com.web_service.converter.SchemaChangeHistoryConverter;
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.dto.RequestDTO;
import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.entity.CurrentTableSchemaEntity;
import com.web_service.entity.SchemaChangeHistoryEntity;
import com.web_service.repository.CurrentTableSchemaRepository;
import com.web_service.repository.SchemaChangeHistoryRepository;
import com.web_service.services.impl.CurrentTableSchemaService;
import com.web_service.services.impl.SchemaChangeHistoryService;

@RunWith(MockitoJUnitRunner.class)
public class SchemaChangeHistoryServiceTest{
	@Mock
	private SchemaChangeHistoryRepository schemaChangeHistoryRepository;
	
	@InjectMocks
	private SchemaChangeHistoryService schemaChangeHistoryService;
	
	@Mock
	private SchemaChangeHistoryConverter schemaChangeHistoryConverter;
	
	private SchemaChangeHistoryEntity schemaChangeHistoryEntity;
	
	private SchemaChangeHistoryDTO schemaChangeHistoryDTO;
	
	@Before
    public void setup() {
		Long id = new Long(1);
		schemaChangeHistoryEntity = new SchemaChangeHistoryEntity();
		schemaChangeHistoryEntity.setId(1L);
		schemaChangeHistoryEntity.setChangeType("nvarchar");
		schemaChangeHistoryEntity.setFieldChange("name");
		schemaChangeHistoryEntity.setOldValue("varchar");
				
		schemaChangeHistoryDTO = new SchemaChangeHistoryDTO();
		schemaChangeHistoryDTO.setChangeType("nvarchar");
		schemaChangeHistoryDTO.setFieldChange("name");
		schemaChangeHistoryDTO.setOldValue("varchar");
    }
	
	@Test
	public void findAll() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<SchemaChangeHistoryEntity> pageSchemaChangeHistory = new PageImpl<>(Collections.singletonList(schemaChangeHistoryEntity));
        Mockito.when(schemaChangeHistoryRepository.findAll(pageable)).thenReturn(pageSchemaChangeHistory);
        Page<SchemaChangeHistoryEntity> schemaChangeHistoryPage = schemaChangeHistoryRepository.findAll(pageable);
        assertEquals(schemaChangeHistoryPage.getNumberOfElements(), 1);
	}
	
	@Test
	public void totalItem() {
		Mockito.when(schemaChangeHistoryRepository.count()).thenReturn(1L);
				
		int totalItem = schemaChangeHistoryService.totalItem();
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void getById() {
		Long id = new Long(1);
		
		schemaChangeHistoryDTO.setId(id);
		Mockito.when(schemaChangeHistoryRepository.findOne(id)).thenReturn(schemaChangeHistoryEntity);
		
		Mockito.when(schemaChangeHistoryConverter.toDTO(schemaChangeHistoryEntity)).thenReturn(schemaChangeHistoryDTO);
		
		SchemaChangeHistoryDTO result = schemaChangeHistoryService.getById(id);
		
		assertTrue(result.getId() == 1);
	}
	
}
