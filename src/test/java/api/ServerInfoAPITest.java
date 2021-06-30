package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.dto.ServerInfoDTO;

public class ServerInfoAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getServerInfors() throws Exception {
		int page = 1;
		int limit = 5;
		String uri = "/api/server_infors?page=" + page + "&limit=" + limit;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void getServerInfoDetail() throws Exception {
		int serverInfoId = 1;
		String uri = "/api/server_infors/" + serverInfoId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void createServerInfor() throws Exception {
		String uri = "/api/server_infors";
		ServerInfoDTO serverInfoDTO = new ServerInfoDTO();
		String host = "8080";
		String domain = "127.1.1.1";
		
		serverInfoDTO.setServerHost(host);
		serverInfoDTO.setServerDomain(domain);
		
		String inputJson = super.mapToJson(serverInfoDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void updateDatabaseInfo() throws Exception {
		int serverInfoId = 1;
		String uri = "/api/server_infors/" + serverInfoId;
		
		ServerInfoDTO serverInfoDTO = new ServerInfoDTO();
		String host = "8080";
		String domain = "127.1.1.1";
		
		serverInfoDTO.setServerHost(host);
		serverInfoDTO.setServerDomain(domain);
		
		String inputJson = super.mapToJson(serverInfoDTO);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void deleteDatabaseInfo() throws Exception {
		int databaseInfoId = 18;
		String uri = "/api/database_infors/" + databaseInfoId;
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
