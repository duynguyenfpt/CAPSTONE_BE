package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.AccountConvertor;
import com.web_service.dto.AccountDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.ServerInfoEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestAccountConverter {

	AccountEntity accountEntity;
	
	AccountDTO accountDTO;
	
	ServerInfoEntity serverInfoEntity;
	
	@InjectMocks
    private static AccountConvertor accountConvertor = new AccountConvertor();
	
	@Before
	public void setUp() {
		accountEntity = new AccountEntity();
		@SuppressWarnings("deprecation")
		Long id = new Long(1);
		
		accountEntity.setId(id);
		accountEntity.setUsername("longvt");
		accountEntity.setEmail("longvthe130282");
		accountEntity.setPhone("0898266025");
		accountEntity.setRole("admin");
		accountEntity.setActive(true);
		
		accountDTO = new AccountDTO();
		accountDTO.setId(id);
		accountDTO.setUsername("longvt");
		accountDTO.setEmail("longvthe130282");
		accountDTO.setPhone("0898266025");
		accountDTO.setRole("admin");
		accountDTO.setActive(true);
	}
	
	@Test
	public void testToDTO() {
		
		AccountDTO accountDTOResult = accountConvertor.toDTO(accountEntity);
		
		assertTrue(accountDTOResult.getId() == (long)1 && accountDTOResult.getUsername().equals("longvt") &&
				accountDTOResult.getEmail().equals("longvthe130282") && accountDTOResult.getPhone().equals("0898266025")
				&& accountDTOResult.getRole().equals("admin") && accountDTOResult.getActive() == true);
	}
	
	@Test
	public void testToEntity() {
		
		AccountEntity accountEntityResult = accountConvertor.toEntity(accountDTO);
		
		assertTrue(accountEntityResult.getUsername().equals("longvt") && 
				accountEntityResult.getEmail().equals("longvthe130282") && accountEntityResult.getPhone().equals("0898266025")
				&& accountEntityResult.getRole().equals("admin") && accountEntityResult.getActive() == true);
	}
	
	@Test
	public void updateEntity() {
		
		AccountEntity accountEntityResult = accountConvertor.toEntity(accountDTO, accountEntity);
		
		assertTrue(accountEntityResult.getEmail().equals("longvthe130282") 
				&& accountEntityResult.getPhone().equals("0898266025")
				&& accountEntityResult.getRole().equals("admin") 
				&& accountEntityResult.getActive() == true);
	}
}
