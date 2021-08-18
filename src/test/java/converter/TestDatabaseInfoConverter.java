package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.AccountConvertor;
import com.web_service.converter.AccountRightConverter;
import com.web_service.converter.DatabaseInfoConverter;
import com.web_service.dto.AccountDTO;
import com.web_service.dto.AccountRightDTO;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.AccountRightEntity;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ServerInfoEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestDatabaseInfoConverter {
	private DatabaseInfoEntity databaseInfoEntity;
	
	private DatabaseInfoDTO databaseInfoDTO;
	
	private ServerInfoEntity serverInfoEntity;
	
	@InjectMocks
    private static DatabaseInfoConverter databaseInfoConverter = new DatabaseInfoConverter();
	
	@Before
	public void setUp() {
		databaseInfoEntity = new DatabaseInfoEntity();
		Long id = new Long(1);
		
		serverInfoEntity = new ServerInfoEntity();
		
		serverInfoEntity.setId(id);
		serverInfoEntity.setServerDomain("10.8.0.1");
		serverInfoEntity.setServerHost("10.8.0.1");
		
		databaseInfoEntity.setId(id);
		databaseInfoEntity.setPort("1344");
		databaseInfoEntity.setUsername("longvthe130282");
		databaseInfoEntity.setPassword("123456");
		databaseInfoEntity.setDatabaseType("posgresql");
		databaseInfoEntity.setDatabaseName("capstone");
		databaseInfoEntity.setAlias("capstone");
		databaseInfoEntity.setSid("system");
		databaseInfoEntity.setServerInfo(serverInfoEntity);
		
		databaseInfoDTO = new DatabaseInfoDTO();
		databaseInfoDTO.setPort("1344");
		databaseInfoDTO.setUsername("longvthe130282");
		databaseInfoDTO.setPassword("123456");
		databaseInfoDTO.setDatabaseType("posgresql");
		databaseInfoDTO.setDatabaseName("capstone");
		databaseInfoDTO.setAlias("capstone");
		databaseInfoDTO.setSid("system");
	}
	
	@Test
	public void testToDTO() {
		
		DatabaseInfoDTO databaseInfoDTOResult = databaseInfoConverter.toDTO(databaseInfoEntity);
		
		assertTrue(databaseInfoDTOResult.getId() == (long)1 && databaseInfoDTOResult.getPort().equals("1344") &&
				databaseInfoDTOResult.getUsername().equals("longvthe130282") 
				&& databaseInfoDTOResult.getPassword().equals("123456")
				&& databaseInfoDTOResult.getDatabaseName().equals("capstone") 
				&& databaseInfoDTOResult.getDatabaseType().equals("posgresql"));
	}
	
	@Test
	public void testToEntity() {
		
		DatabaseInfoEntity databaseInfoEntityResult = databaseInfoConverter.toEntity(databaseInfoDTO);
		
		assertTrue(databaseInfoEntityResult.getPort().equals("1344") 
				&& databaseInfoEntityResult.getUsername().equals("longvthe130282") 
				&& databaseInfoEntityResult.getPassword().equals("123456")
				&& databaseInfoEntityResult.getDatabaseName().equals("capstone") 
				&& databaseInfoEntityResult.getDatabaseType().equals("posgresql"));
	}
	
	@Test
	public void updateEntity() {
		
		DatabaseInfoEntity databaseInfoEntityResult = databaseInfoConverter.toEntity(databaseInfoDTO, databaseInfoEntity);
		
		assertTrue(databaseInfoEntityResult.getPort().equals("1344") 
				&& databaseInfoEntityResult.getUsername().equals("longvthe130282") 
				&& databaseInfoEntityResult.getPassword().equals("123456")
				&& databaseInfoEntityResult.getDatabaseName().equals("capstone") 
				&& databaseInfoEntityResult.getDatabaseType().equals("posgresql"));	
	}
}
