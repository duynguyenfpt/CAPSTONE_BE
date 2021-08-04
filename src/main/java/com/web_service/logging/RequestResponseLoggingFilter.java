package com.web_service.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.web_service.entity.mongo.ActionLogEntity;
import com.web_service.repository.ActionLogRepository;

@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {
	@Autowired
	ActionLogRepository actionLogRepository;

    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(req);

		ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper(
				(HttpServletResponse) response);
		chain.doFilter(wrappedRequest, responseCacheWrapperObject);
		String uri = req.getRequestURI();

		if(!uri.contains("/authenticate") && !uri.contains("/register") && !uri.contains("/change_password")) {
			String requestBody = logRequestBody(wrappedRequest);
//	        String responseBody = getResponseBody(responseCacheWrapperObject);

			ActionLogEntity actionLogEntity = new ActionLogEntity();
			
			actionLogEntity.setRequestMethod(req.getMethod());
			actionLogEntity.setUserName(getUserName());
			actionLogEntity.setBodyRequest(requestBody);
//			actionLogEntity.setResponse(responseBody);
			actionLogEntity.setPath(req.getRequestURI());
			actionLogEntity.setStatusCode(responseCacheWrapperObject.getStatusCode());
			
			actionLogRepository.save(actionLogEntity);
		}
        
		responseCacheWrapperObject.copyBodyToResponse();
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 System.out.println("LogFilter init!");
		
	}

	@Override
	public void destroy() {
		System.out.println("LogFilter destroy!");
	}
	
	static String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	static String getResponseBody(ContentCachingResponseWrapper responseCacheWrapperObject) throws UnsupportedEncodingException {
		byte[] responseArray = responseCacheWrapperObject.getContentAsByteArray();
		String responseStr = new String(responseArray, responseCacheWrapperObject.getCharacterEncoding());
		
		return responseStr;
	}

	static String extractPostRequestBody(HttpServletRequest request) throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
		return "";
	}
	
	private static String logRequestBody(ContentCachingRequestWrapper request) {

        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            try {
                String requestBody = new String(buf, 0, buf.length, request.getCharacterEncoding());
                return requestBody;
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }
	
}

