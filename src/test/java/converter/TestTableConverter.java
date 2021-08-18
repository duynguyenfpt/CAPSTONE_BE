package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.ServerInfoConverter;
import com.web_service.converter.TableConverter;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.TableDTO;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.entity.TableEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestTableConverter {
	private TableEntity tableEntity;
	
	private TableDTO tableDTO;
	
	private DatabaseInfoEntity databaseInfoEntity;
	
	private ServerInfoEntity serverInfoEntity;
	
	@InjectMocks
    private static TableConverter tableConverter = new TableConverter();
	
	@Before
	public void setUp() {
		tableEntity = new TableEntity();
		Long id = new Long(1);
		serverInfoEntity = new ServerInfoEntity();
		serverInfoEntity.setId(id);
		serverInfoEntity.setServerDomain("10.8.0.1");
		serverInfoEntity.setServerHost("10.8.0.1");
		
		databaseInfoEntity = new DatabaseInfoEntity();
		databaseInfoEntity.setId(id);
		databaseInfoEntity.setPort("1344");
		databaseInfoEntity.setUsername("longvthe130282");
		databaseInfoEntity.setPassword("123456");
		databaseInfoEntity.setDatabaseType("posgresql");
		databaseInfoEntity.setDatabaseName("capstone");
		databaseInfoEntity.setAlias("capstone");
		databaseInfoEntity.setSid("system");
		databaseInfoEntity.setServerInfo(serverInfoEntity);
		
		tableEntity.setId(id);
		tableEntity.setTableName("student");
		tableEntity.setDatabaseInfo(databaseInfoEntity);
		
		tableDTO = new TableDTO();
		tableDTO.setTableName("student");
	}
	
	@Test
	public void testToDTO() {
		
		TableDTO tableDTOResult = tableConverter.toDTO(tableEntity);
		
		assertTrue(tableDTOResult.getId() == (long)1 
				&& tableDTOResult.getTableName().equals("student"));
	}
	
	@Test
	public void testToEntity() {
		
		TableEntity tableEntityResult = tableConverter.toEntity(tableDTO);
		
		assertTrue(tableEntityResult.getTableName().equals("student"));
	}
	
	@Test
	public void updateEntity() {
		
		TableEntity tableEntityResult = tableConverter.toEntity(tableDTO, tableEntity);
		
		assertTrue(tableEntityResult.getTableName().equals("student"));	
	}
}
