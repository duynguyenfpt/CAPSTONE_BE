package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.dto.AddColumnTableRequestDTO;
import com.web_service.dto.DatabaseInfoDTO;

public class DatabaseInfoAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getDatabaseInfoExcludeKeyword() throws Exception {
		int page = 1;
		int limit = 5;
		String uri = "/api/database_infors?page=" + page + "&limit=" + limit;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void getDatabaseInfoIncludeKeyword() throws Exception {
		int page = 1;
		int limit = 5;
		String keyword = "test";
		String uri = "/api/database_infors?page=" + page + "&limit=" + limit + "&keyword=" + keyword;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void createDatabaseInfo() throws Exception {
		String uri = "/api/database_infors";
		DatabaseInfoDTO databaseInfoDTO = new DatabaseInfoDTO();
		String port = "8080";
		String username = "root";
		String password = "123456";
		String databaseName = "webservice";
		String databaseType = "mySql";
		long serverInfoId = 1;
		
		databaseInfoDTO.setPort(port);
		databaseInfoDTO.setUsername(username);
		databaseInfoDTO.setPassword(password);
		databaseInfoDTO.setDatabaseName(databaseName);
		databaseInfoDTO.setDatabaseType(databaseType);
		databaseInfoDTO.setServerInforId(serverInfoId);
		
		String inputJson = super.mapToJson(databaseInfoDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void updateDatabaseInfo() throws Exception {
		int databaseInfoId = 1;
		String uri = "/api/database_infors/" + databaseInfoId;
		
		DatabaseInfoDTO databaseInfoDTO = new DatabaseInfoDTO();
		String port = "8080";
		String username = "root";
		String password = "123456";
		String databaseName = "webservice";
		String databaseType = "mySql";
		long serverInfoId = 1;
		
		databaseInfoDTO.setPort(port);
		databaseInfoDTO.setUsername(username);
		databaseInfoDTO.setPassword(password);
		databaseInfoDTO.setDatabaseName(databaseName);
		databaseInfoDTO.setDatabaseType(databaseType);
		databaseInfoDTO.setServerInforId(serverInfoId);
		
		String inputJson = super.mapToJson(databaseInfoDTO);
		
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
