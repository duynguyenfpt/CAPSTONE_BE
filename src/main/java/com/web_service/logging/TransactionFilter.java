package com.web_service.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.web_service.api.output.ObjectOuput;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.RightEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.RightRepository;

@Component
@Order(1)
public class TransactionFilter implements Filter {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RightRepository rightRepository;
	
    private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String method = req.getMethod();
		String uri = req.getRequestURI();
		
		AccountEntity accountEntity = getCurrentAccount();
		
		List<RightEntity> rights = rightRepository.findRightByAccountId(accountEntity.getId());
		boolean isPermission = checkPermission(uri, method, rights, accountEntity);
		
		if (accountEntity.getActive() == false || isPermission == false) {
			ObjectOuput<String> result = new ObjectOuput<>();
			result.setData("");
			result.setMessage("Access denied");
			result.setCode("403");
			res.setStatus(403);
			res.setContentType("application/json");
			mapper.writeValue(res.getWriter(), result);
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LogFilter init!");
	}

	@Override
	public void destroy() {
		System.out.println("LogFilter destroy!");

	}
	
	private AccountEntity getCurrentAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		AccountEntity accountEntity = accountRepository.findByUsername(auth.getName());
		
		return accountEntity;
	}
	
	private boolean checkPermission(String uri, String method, List<RightEntity> rightEntities, AccountEntity accountEntity) {
		if(accountEntity.getRole().toLowerCase().trim().equals("admin")
				&& (uri.contains("rights") || uri.contains("accounts"))) {
			return true;
		}
		
		if(uri.contains("api/me") || uri.contains("api/authenticate") || uri.contains("api/register")) {
			return true;
		}
		
		boolean isPermission =  rightEntities.stream()
				.anyMatch(e -> uri.contains(e.getPath()) && method.equals(e.getMethod()));
		
		return isPermission;
	}
}