package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.TableDTO;

public class TableAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getTables() throws Exception {
		int page = 1;
		int limit = 5;
		String uri = "/api/tables?page=" + page + "&limit=" + limit;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void getTableDetail() throws Exception {
		int tableId = 1;
		String uri = "/api/tables/" + tableId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void createTableId() throws Exception {
		String uri = "/api/tables";
		TableDTO tableDTO = new TableDTO();
		long databaseInfoId = 1;
		String tableName = "Student";
		
		tableDTO.setTableName(tableName);
		tableDTO.setDatabaseInforId(databaseInfoId);
		
		String inputJson = super.mapToJson(tableDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void updateDatabaseInfo() throws Exception {
		int serverInfoId = 1;
		String uri = "/api/tables/" + serverInfoId;
		
		TableDTO tableDTO = new TableDTO();
		long databaseId = 1;
		String tableName = "Class";
		
		tableDTO.setDatabaseInforId(databaseId);
		tableDTO.setTableName(tableName);
		
		String inputJson = super.mapToJson(tableDTO);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void deleteDatabaseInfo() throws Exception {
		int tableId = 18;
		String uri = "/api/tables/" + tableId;
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
