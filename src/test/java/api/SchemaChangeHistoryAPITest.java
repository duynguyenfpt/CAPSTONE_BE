package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.dto.RequestDTO;

public class SchemaChangeHistoryAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getSchemaChangeHistory() throws Exception {
		int page = 1;
		int limit = 5;
		String uri = "/api/schema_change_history?page=" + page + "&limit=" + limit;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void getSchemaChangeHistoryDetail() throws Exception {
		int schemaChangeHistoryId = 1;
		String uri = "/api/schema_change_history/" + schemaChangeHistoryId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
	
	@Test
	public void searchSchemaChangeHistoryDetail() throws Exception {
		int page = 1;
		int limit = 5;
		int tableId = 1;
		String typeChange = "deleted";
		String uri = "/api/schema_change_history/search?page=" + page + "&limit=" + limit + "&tableId=" + tableId + "&typeChange=" + typeChange;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
}
