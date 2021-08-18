package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.ETLRequestConverter;
import com.web_service.dto.ETLRequestDTO;
import com.web_service.entity.ETLEntity;
import com.web_service.entity.RequestEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestETLREquestConverter {
	private ETLEntity etlEntity;
	
	private ETLRequestDTO etlRequestDTO;
	
	private RequestEntity requestEntity;
	
	@InjectMocks
    private static ETLRequestConverter etlRequestConverter = new ETLRequestConverter();
	
	@Before
	public void setUp() {
		etlEntity = new ETLEntity();
		Long id = new Long(1);
		
		requestEntity = new RequestEntity();
		requestEntity.setId(id);
		requestEntity.setRequestType("SyncTable");
		requestEntity.setStatus("pending");
		
		etlEntity.setId(id);
		etlEntity.setQuery("select * from :sales.public.employees:");
		etlEntity.setQueryType("sql");
		etlEntity.setRequest(requestEntity);
		
		etlRequestDTO = new ETLRequestDTO();
		
		etlRequestDTO.setQuery("select * from :sales.public.employees:");
		etlRequestDTO.setQueryType("sql");
	}
	
	@Test
	public void testToDTO() {
		
		ETLRequestDTO etlRequestDTOResult = etlRequestConverter.toDTO(etlEntity);
		
		assertTrue(etlRequestDTOResult.getId() == (long)1 
				&& etlRequestDTOResult.getQuery().equals("select * from :sales.public.employees:")
				&& etlRequestDTOResult.getQueryType().equals("sql"));
	}
	
	@Test
	public void testToEntity() {
		
		ETLEntity etlEntityResult = etlRequestConverter.toEntity(etlRequestDTO);
		
		assertTrue(etlEntityResult.getQuery().equals("select * from :sales.public.employees:")
				&& etlEntityResult.getQueryType().equals("sql"));
	}
	
	@Test
	public void updateEntity() {
		
		ETLEntity etlEntityResult = etlRequestConverter.toEntity(etlRequestDTO, etlEntity);
		
		assertTrue(etlEntityResult.getQuery().equals("select * from :sales.public.employees:")
				&& etlEntityResult.getQueryType().equals("sql"));	
	}
}
