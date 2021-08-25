package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.dto.AccountRightDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.RightEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.AccountRightRepository;
import com.web_service.repository.RightRepository;
import com.web_service.services.impl.AccountRightService;

@RunWith(MockitoJUnitRunner.class)
public class AccountRightServiceTest {
	private AccountRightDTO accountRightDTO;
	
	private AccountEntity accountEntity;
	
	private RightEntity rightEntity;
	
	@Mock
	AccountRightRepository accountRightRepository;
	
	@Mock
	RightRepository rightRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	AccountRightService accountRightService = new AccountRightService();
	
	@Before
    public void setup() {
		Long id = new Long(1);
		accountEntity = new AccountEntity();
		accountEntity.setId(id);
		accountEntity.setUsername("longvt");
		accountEntity.setEmail("longvt@gmail.com");
		accountEntity.setRole("admin");
		
		rightEntity = new RightEntity();
		rightEntity.setId(id);
		rightEntity.setPath("database_infors");
		rightEntity.setMethod("POST");
		
		Long rightIds[] = {1L};
		accountRightDTO = new AccountRightDTO();
		accountRightDTO.setAccountId(id);
		accountRightDTO.setRightIds(rightIds);
    }
	
//	@Test
//	public void createRightForAccount() {
//		Mockito.when(rightRepository.findOne(1L)).thenReturn(rightEntity);
//		Mockito.when(accountRepository.findOne(1L)).thenReturn(accountEntity);
//		
//		assertEquals(1, 1);
//	}
//	
	@Test
	public void deleteAccountRight() {
		Long rightIds[] = {1L};
		accountRightService.delete(1L, rightIds);
		
		verify(accountRightRepository, times(1)).deleteRight(1L, 1L);
	}
}
