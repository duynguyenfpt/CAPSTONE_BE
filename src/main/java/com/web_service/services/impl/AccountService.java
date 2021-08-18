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
		AccountEntity accountEntity = accountConvertor.toEntity(accountRightDTO.getAccount());
		accountEntity.setPassword(bcryptEncoder.encode(PASSWORD_DEFAULT));
		accountRepository.save(accountEntity);
		if(accountRightDTO.isCopyRight()) {
			List<AccountRightEntity> accountRightEntities = accountRightRepository.findAllByAccountId(accountRightDTO.getAccountId());
			for (AccountRightEntity accountRightEntity : accountRightEntities) {
				AccountRightEntity newAccountRightEntity = new AccountRightEntity();
				newAccountRightEntity.setAccount(accountEntity);
				newAccountRightEntity.setRight(accountRightEntity.getRight());
				accountRightRepository.save(newAccountRightEntity);
			}
		} else {
			for (long rightId : accountRightDTO.getRightIds()) {
				RightEntity rightEntity = rightRepository.findOne(rightId);
				if(rightEntity != null) {
					AccountRightEntity accountRightEntity = new AccountRightEntity();
					accountRightEntity.setAccount(accountEntity);
					accountRightEntity.setRight(rightEntity);
					accountRightEntity = accountRightRepository.save(accountRightEntity);
				}
			}
		}
	}

	@Transactional
	@Override
	public void forgotPassword(String username) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		AccountEntity accountEntity = accountRepository.findByUsername(username);
		String newPassword = generateRandomPassword(6);
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
}
