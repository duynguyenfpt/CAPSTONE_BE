package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.web_service.converter.AccountConvertor;
import com.web_service.dto.AccountDTO;
import com.web_service.dto.JobDTO;
import com.web_service.dto.TableDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.services.impl.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest{
	@InjectMocks
	AccountService accountService = new AccountService();
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	AccountConvertor accountConvertor;
	
	private PasswordEncoder bcryptEncoder;
	
	private AccountEntity accountEntity;
	
	private AccountDTO accountDTO;
	
	@Before
	public void setUp() {
		accountEntity = new AccountEntity();
		accountEntity.setId(1L);
		accountEntity.setUsername("longvt");
		accountEntity.setEmail("longvt@gmail.com");
		accountEntity.setRole("admin");
		
		accountDTO = new AccountDTO();
		accountDTO.setUsername("longvt");
		accountDTO.setEmail("longvt@gmail.com");
		accountDTO.setRole("admin");
	}
	
	@Test
	public void getAccountsList() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<AccountEntity> accountPage = new PageImpl<>(Collections.singletonList(accountEntity));
        Mockito.when(accountRepository.getAllAccount("", pageable)).thenReturn(accountPage);
        Page<AccountEntity> accounts = accountRepository.getAllAccount("", pageable);
        assertEquals(accounts.getNumberOfElements(), 1);
	}
	
	@Test
	public void totalItem() {		
		Mockito.when(accountRepository.countAccount("")).thenReturn(1);
				
		long totalItem = accountService.totalItem("");
		
		assertTrue(totalItem == 1);
	}
	
	@Test
	public void getAccountById() {
		accountDTO.setId(1L);
		Mockito.when(accountRepository.findOne(1L)).thenReturn(accountEntity);
		
		Mockito.when(accountConvertor.toDTO(accountEntity)).thenReturn(accountDTO);
		
		AccountDTO result = accountService.getById(1L);
		
		assertTrue(result.getId() == 1);
	}
	
	@Test
	public void getTableByIdWithBoundary() {
		Long id = new Long(9223372036854775807L);
		
		accountDTO.setId(id);
		Mockito.when(accountRepository.findOne(id)).thenReturn(accountEntity);
		
		Mockito.when(accountConvertor.toDTO(accountEntity)).thenReturn(accountDTO);
		
		AccountDTO result = accountService.getById(id);
		
		assertTrue(result.getId() == 9223372036854775807L);
	}
	
	@Test
	public void getJobByIdWithZero() {
		Long id = new Long(0);
		
		accountDTO.setId(id);
		Mockito.when(accountRepository.findOne(id)).thenReturn(accountEntity);
		
		Mockito.when(accountConvertor.toDTO(accountEntity)).thenReturn(accountDTO);
		
		AccountDTO result = accountService.getById(id);
		
		assertTrue(result.getId() == 0);
	}
	
	@Test
	public void createAccount() {
		
		AccountDTO accountResult = new AccountDTO();
		accountResult.setId(1L);
		accountResult.setUsername("longvt");
		accountResult.setEmail("longvt@gmail.com");
		accountResult.setRole("admin");
		
		Mockito.when(accountConvertor.toEntity(accountDTO)).thenReturn(accountEntity);
				
		Mockito.when(accountConvertor.toDTO(accountEntity)).thenReturn(accountResult);
		
		Mockito.when(accountRepository.save(accountEntity)).thenReturn(accountEntity);
				
		AccountDTO result = accountService.save(accountDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void updateAccount() {
		accountDTO.setId(1L);
		Mockito.when(accountRepository.findOne(1L)).thenReturn(accountEntity);
		
		Mockito.when(accountConvertor.toEntity(accountDTO, accountEntity)).thenReturn(accountEntity);
		
		Mockito.when(accountConvertor.toDTO(accountEntity)).thenReturn(accountDTO);
		
		Mockito.when(accountRepository.save(accountEntity)).thenReturn(accountEntity);
		
		AccountDTO result = accountService.save(accountDTO);
		
		assertEquals(result.getId(), 1);
	}
	
	@Test
	public void resetPassword() {
		Mockito.when(accountRepository.findOne(1L)).thenReturn(accountEntity);
		
		accountService.resetPassword(1L);
		
		assertNotNull(accountEntity.getPassword());
	}
	
	@Test
	public void findByUserName() {
		accountDTO.setId(1L);
		Mockito.when(accountRepository.findByUsername("longvt")).thenReturn(accountEntity);
		Mockito.when(accountConvertor.toDTO(accountEntity)).thenReturn(accountDTO);
		
		AccountDTO result = accountService.findByUserName("longvt");
		
		assertEquals(result.getId(), 1);
	}
	
//	@Test
//	public void createAccountWithRights() {
//		
//	}
	
}
