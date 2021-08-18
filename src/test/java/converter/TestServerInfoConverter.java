package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.ServerInfoConverter;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.ServerInfoEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestServerInfoConverter {
	private ServerInfoEntity serverInfoEntity;
	
	private ServerInfoDTO serverInfoDTO;
	
	@InjectMocks
    private static ServerInfoConverter serverInfoConverter = new ServerInfoConverter();
	
	@Before
	public void setUp() {
		serverInfoEntity = new ServerInfoEntity();
		Long id = new Long(1);
		
		serverInfoEntity.setId(id);
		serverInfoEntity.setServerDomain("10.8.0.1");
		serverInfoEntity.setServerHost("10.8.0.1");
		
		serverInfoDTO = new ServerInfoDTO();
		serverInfoDTO.setServerDomain("10.8.0.1");
		serverInfoDTO.setServerHost("10.8.0.1");
	}
	
	@Test
	public void testToDTO() {
		
		ServerInfoDTO serverInfoDTOResult = serverInfoConverter.toDTO(serverInfoEntity);
		
		assertTrue(serverInfoDTOResult.getId() == (long)1 
				&& serverInfoDTOResult.getServerDomain().equals("10.8.0.1")
				&& serverInfoDTOResult.getServerHost().equals("10.8.0.1"));
	}
	
	@Test
	public void testToEntity() {
		
		ServerInfoEntity serverInfoEntityResult = serverInfoConverter.toEntity(serverInfoDTO);
		
		assertTrue(serverInfoEntityResult.getServerDomain().equals("10.8.0.1")
				&& serverInfoEntityResult.getServerHost().equals("10.8.0.1"));
	}
	
	@Test
	public void updateEntity() {
		
		ServerInfoEntity serverInfoEntityResult = serverInfoConverter.toEntity(serverInfoDTO, serverInfoEntity);
		
		assertTrue(serverInfoEntityResult.getServerDomain().equals("10.8.0.1") 
				&& serverInfoEntityResult.getServerHost().equals("10.8.0.1"));	
	}
}
