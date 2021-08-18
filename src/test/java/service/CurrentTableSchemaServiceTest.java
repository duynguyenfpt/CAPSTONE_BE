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

import com.web_service.converter.CurrentTableSchemaConverter;
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.entity.CurrentTableSchemaEntity;
import com.web_service.repository.CurrentTableSchemaRepository;
import com.web_service.services.impl.CurrentTableSchemaService;

@RunWith(MockitoJUnitRunner.class)
public class CurrentTableSchemaServiceTest {

	@Mock
	private CurrentTableSchemaRepository currentTableSchemaRepository;
	
	@InjectMocks
	private CurrentTableSchemaService currentTableSchemaService;
	
	@Mock
	private CurrentTableSchemaConverter currentTableSchemaConverter;
	
	private CurrentTableSchemaEntity currentTableSchemaEntity;
	
	private CurrentTableSchemaDTO currentTableSchemaDTO;
	
	@Before
    public void setup() {
		Long id = new Long(1);
		currentTableSchemaEntity = new CurrentTableSchemaEntity();
		currentTableSchemaEntity.setId(1L);
		currentTableSchemaEntity.setColumnName("name");
		currentTableSchemaEntity.setColumnType("Varchar");
		currentTableSchemaEntity.setDefaultValue("Long");
		
		currentTableSchemaDTO = new CurrentTableSchemaDTO();
		currentTableSchemaDTO.setColumnName("name");
		currentTableSchemaDTO.setColumnType("Varchar");
		currentTableSchemaDTO.setDefaultValue("Long");
    }
	
	@Test
	public void findAll() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<CurrentTableSchemaEntity> pageCurrentTableSchema = new PageImpl<>(Collections.singletonList(currentTableSchemaEntity));
        Mockito.when(currentTableSchemaRepository.findAll(pageable)).thenReturn(pageCurrentTableSchema);
        Page<CurrentTableSchemaEntity> currentTableSchemas = currentTableSchemaRepository.findAll(pageable);
        assertEquals(currentTableSchemas.getNumberOfElements(), 1);
	}
	
	@Test
	public void totalItem() {
		List<CurrentTableSchemaEntity> currentTableSchemaEntities = new ArrayList<>();
		currentTableSchemaEntities.add(currentTableSchemaEntity);
		
		Mockito.when(currentTableSchemaRepository.countByTableInfoId(1L)).thenReturn(currentTableSchemaEntities);
				
		int totalItem = currentTableSchemaService.totalItem(1L);
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void getById() {
		Long id = new Long(1);
		
		currentTableSchemaDTO.setId(id);
		Mockito.when(currentTableSchemaRepository.findOne(id)).thenReturn(currentTableSchemaEntity);
		
		Mockito.when(currentTableSchemaConverter.toDTO(currentTableSchemaEntity)).thenReturn(currentTableSchemaDTO);
		
		CurrentTableSchemaDTO result = currentTableSchemaService.getById(id);
		
		assertTrue(result.getId() == 1);
	}
}
