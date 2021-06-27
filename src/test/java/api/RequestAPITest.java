package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.dto.RequestDTO;

public class RequestAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getRequests() throws Exception {
		int page = 1;
		int limit = 5;
		String uri = "/api/requests?page=" + page + "&limit=" + limit;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void getRequestDetail() throws Exception {
		int requestId = 1;
		String uri = "/api/requests/" + requestId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void createRequest() throws Exception {
		String uri = "/api/requests";
		RequestDTO requestDTO = new RequestDTO();
		String status = "request";
		String requestType = "DONG_BO";
		long creatorId = 1;
		
		requestDTO.setStatus(status);
		requestDTO.setRequestType(requestType);
		requestDTO.setCreatorId(creatorId);
		
		String inputJson = super.mapToJson(requestDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(201, statusCode);
	}
	
	@Test
	public void updateRequest() throws Exception {
		int requestId = 1;
		String uri = "/api/requests/" + requestId;
		
		RequestDTO requestDTO = new RequestDTO();
		String status = "request";
		String requestType = "DONG_BO";
		long approvedById = 1;
		
		requestDTO.setStatus(status);
		requestDTO.setRequestType(requestType);
		requestDTO.setApprovedById(approvedById);
		
		String inputJson = super.mapToJson(requestDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(200, statusCode);
	}
	
	@Test
	public void deleteDatabaseInfo() throws Exception {
		int requestId = 18;
		String uri = "/api/requests/" + requestId;
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
