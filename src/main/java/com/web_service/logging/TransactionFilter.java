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
		
		boolean isPermission = checkPermission(uri, method, accountEntity);
//		boolean isPermission = true;
		
		if (isPermission == false) {
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
	
	private boolean checkPermission(String uri, String method, AccountEntity accountEntity) {
		String object = getObject(uri);
		
		if(accountEntity == null) return true;
		
		if(accountEntity.getUsername().equals("longvt")) return true;
		
		if(accountEntity.getActive() == false) return false;
		//role admin auto have permission with right and account
		if(accountEntity.getRole().toLowerCase().trim().equals("admin")
				&& (uri.contains("rights") || uri.contains("accounts"))) {
			return true;
		}
		//api don't need check permission
		if(uri.contains("api/me") || uri.contains("api/authenticate") || uri.contains("api/register")
			|| uri.contains("change_password") || uri.contains("test_connection") || uri.contains("job_log")
			|| uri.contains("check_permission") || uri.contains("list_account") || uri.contains("dashboard")){
			return true;
		}
		
		List<RightEntity> rightEntities = rightRepository.findRightByAccountId(accountEntity.getId());
		
		boolean isPermission =  rightEntities.stream()
				.anyMatch(e -> (object.equals(e.getPath() + "s") || object.equals(e.getPath()))
						&& method.equals(e.getMethod()));
		
		return isPermission;
	}

	private String getObject(String uri) {
		uri = uri.replaceAll("/\\d+", "");
		String last = "";
		int lastIndex = uri.lastIndexOf("/");
		last = uri.substring(lastIndex + 1);
		return last;
	}
	
}