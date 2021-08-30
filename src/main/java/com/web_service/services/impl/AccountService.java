package com.web_service.services.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web_service.converter.AccountConvertor;
import com.web_service.dto.AccountDTO;
import com.web_service.dto.AccountRightDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.AccountRightEntity;
import com.web_service.entity.RightEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.AccountRightRepository;
import com.web_service.repository.RightRepository;
import com.web_service.services.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountConvertor accountConvertor;
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	@Autowired
	AccountRightRepository accountRightRepository;
	
	@Autowired
	RightRepository rightRepository;
	
	@Autowired
	JavaMailSender emailSender;

	String PASSWORD_DEFAULT = "123456";
	
	@Override
	public List<AccountDTO> findAll(String keyword, Pageable pageable) {
		List<AccountDTO> results = new ArrayList<>();
		//Get all account
		List<AccountEntity> entities = accountRepository.getAllAccount(keyword, pageable).getContent();
		for (AccountEntity item: entities) {
			AccountDTO accountDTO = accountConvertor.toDTO(item);
			results.add(accountDTO);
		}
		return results;
	}

	@Override
	public int totalItem(String keyword) {
		return (int) accountRepository.countAccount(keyword);
	}

	@Override
	public AccountDTO getById(long id) {
		AccountEntity accountEntity = accountRepository.findOne(id);
		AccountDTO accountDTO = accountConvertor.toDTO(accountEntity);
		return accountDTO;
	}

	@Override
	public AccountDTO save(AccountDTO accountDTO) {
		AccountEntity accountEntity = new AccountEntity();
	
		if (accountDTO.getId() != null) {
			AccountEntity oldAccountEntity = accountRepository.findOne(accountDTO.getId());
			accountEntity = accountConvertor.toEntity(accountDTO, oldAccountEntity);
		} else {
			accountEntity = accountConvertor.toEntity(accountDTO);
			accountEntity.setPassword(PASSWORD_DEFAULT);
		}

		accountEntity = accountRepository.save(accountEntity);
		return accountConvertor.toDTO(accountEntity);
	}

	@Override
	public void resetPassword(long id) {
		AccountEntity accountEntity = accountRepository.findOne(id);
		accountEntity.setPassword(PASSWORD_DEFAULT);
		
		accountRepository.save(accountEntity);
	}
	
	@Override
	public AccountDTO findByUserName(String username) {
		AccountEntity accountEntity = accountRepository.findByUsername(username);
		AccountDTO accountDTO = accountConvertor.toDTO(accountEntity);
		
		return accountDTO;
	}

	@Transactional
	@Override
	public void createAccountWithRights(AccountRightDTO accountRightDTO) {
		//Create account
		AccountEntity accountEntity = accountConvertor.toEntity(accountRightDTO.getAccount());
		accountEntity.setPassword(bcryptEncoder.encode(PASSWORD_DEFAULT));
		accountEntity.setActive(true);
		accountRepository.save(accountEntity);
		
		
		if(accountRightDTO.getRightOption().equals("copy")) {
			//Get list right from another account
			List<AccountRightEntity> accountRightEntities = accountRightRepository.findAllByAccountId(accountRightDTO.getAccountId());
			for (AccountRightEntity accountRightEntity : accountRightEntities) {
				AccountRightEntity newAccountRightEntity = new AccountRightEntity();
				newAccountRightEntity.setAccount(accountEntity);
				newAccountRightEntity.setRight(accountRightEntity.getRight());
				accountRightRepository.save(newAccountRightEntity);
			}
		} else if(accountRightDTO.getRightOption().equals("select")){
			//Add right for account
			for (long rightId : accountRightDTO.getRightIds()) {
				RightEntity rightEntity = rightRepository.findOne(rightId);
				if(rightEntity != null) {
					AccountRightEntity accountRightEntity = new AccountRightEntity();
					accountRightEntity.setAccount(accountEntity);
					accountRightEntity.setRight(rightEntity);
					accountRightEntity = accountRightRepository.save(accountRightEntity);
				}
			}
		} else {
			setRightDefault(accountEntity);
		}
	}

	@Transactional
	@Override
	public void forgotPassword(String username) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		//Find account by username
		AccountEntity accountEntity = accountRepository.findByUsername(username);
		//Get random password
		String newPassword = generateRandomPassword(6);
		//Encode
		accountEntity.setPassword(bcryptEncoder.encode(newPassword));
		accountRepository.save(accountEntity);
		
        message.setTo(accountEntity.getEmail());
        message.setSubject("Change your password");
        message.setText(createMessage(username, newPassword));
         
        emailSender.send(message);		
	}
	
	private String createMessage(String username, String password) {
		String message = "";
		message += "Hello, " + username + ".\n";
		message += "The password of " + username + ": " + password;
		message += "\n\nThank you";
		message += "\n-------------";
		message += "\nThis message is sent from warning system. Please do not reply to this message.";
		
		return message;
	}
	
	public static String generateRandomPassword(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }

	@Override
	public AccountEntity findByUserNameEntity(String username) {
		return accountRepository.findByUsername(username);
	}
	
	private void setRightDefault(AccountEntity accountEntity) {
		if(accountEntity.getRole().equals("viewer")) {
			createAccountRight("GET", "server_infor", accountEntity);
			createAccountRight("POSTPUTDELETEGET", "database_infor", accountEntity);
			createAccountRight("POST", "test_connection", accountEntity);
			createAccountRight("POSTPUTDELETEGET", "table", accountEntity);
			createAccountRight("PUT", "merge_request", accountEntity);
			createAccountRight("POSTDELETEGET", "request", accountEntity);
			createAccountRight("GET", "download_csv", accountEntity);
			createAccountRight("GET", "job", accountEntity);
			createAccountRight("GET", "column", accountEntity);
			createAccountRight("GET", "schema_change_history", accountEntity);
			createAccountRight("POST", "share", accountEntity);
			createAccountRight("GETPOST", "note", accountEntity);
		}else if(accountEntity.getRole().equals("engineer")) {
			createAccountRight("POSTPUTDELETEGET", "server_infor", accountEntity);
			createAccountRight("POSTPUTDELETEGET", "database_infor", accountEntity);
			createAccountRight("POST", "test_connection", accountEntity);
			createAccountRight("POSTPUTDELETEGET", "table", accountEntity);
			createAccountRight("GET", "action_log", accountEntity);
			createAccountRight("GETPUT", "request", accountEntity);
			createAccountRight("GET", "download_csv", accountEntity);
			createAccountRight("GETPOST", "note", accountEntity);
			createAccountRight("POSTPUTDELETEGET", "job", accountEntity);
			createAccountRight("GET", "column", accountEntity);
		}
	}
	
	private void createAccountRight(String methods, String path, AccountEntity accountEntity) {
		List<RightEntity> listRight = rightRepository.findRightByPath(path);
		
		if(listRight.size() > 0) {
			for (RightEntity rightEntity : listRight) {
				if(methods.contains(rightEntity.getMethod())) {
					AccountRightEntity accountRightEntity = new AccountRightEntity();
					accountRightEntity.setAccount(accountEntity);
					accountRightEntity.setRight(rightEntity);
					try {
						accountRightEntity = accountRightRepository.save(accountRightEntity);
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}

	}

	@Override
	public List<AccountDTO> getAccountActive(Pageable pageable) {
		List<AccountDTO> results = new ArrayList<>();
		//Get all account
		List<AccountEntity> entities = accountRepository.getAllAccountActive(pageable).getContent();
		for (AccountEntity item: entities) {
			AccountDTO accountDTO = accountConvertor.toDTO(item);
			results.add(accountDTO);
		}
		return results;
	}

	@Override
	public int totalItemAccountActive() {
		return accountRepository.countAccountActive();
	}
}
