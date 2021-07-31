package com.web_service.security.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web_service.dto.AccountDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.repository.AccountRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(account.getUserName(), account.getPassword(), new ArrayList<>());
	}
	
	public AccountEntity save(AccountDTO account) {
		AccountEntity newAcc = new AccountEntity();
		newAcc.setUserName(account.getUsername());
		newAcc.setPassword(bcryptEncoder.encode(account.getPassword()));
		newAcc.setEmail(account.getEmail());
		newAcc.setPhone(account.getPhone());
		newAcc.setRole(account.getRole());
		return accountRepository.save(newAcc);
	}
	
	public void resetPassword(long accountId) {
		String DEFAULT_PASSWORD = "123456";
		AccountEntity accountEntity = accountRepository.findOne(accountId);
		accountEntity.setPassword(bcryptEncoder.encode(DEFAULT_PASSWORD));
		
		accountRepository.save(accountEntity);	
	}
	
	public String changePassword(String oldPassword, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AccountEntity accountEntity = accountRepository.findByUsername(auth.getName());
		
		if (passwordEncoder.matches(oldPassword, accountEntity.getPassword())) {
			accountEntity.setPassword(bcryptEncoder.encode(newPassword));
			accountRepository.save(accountEntity);
			return "success";
		} else {
			return "invalide_password";
		}
	}

}
