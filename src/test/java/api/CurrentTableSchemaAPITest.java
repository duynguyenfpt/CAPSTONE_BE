package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class CurrentTableSchemaAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getCurrentTableSchemaByTable() throws Exception {
		int tableId = 1;
		int page = 1;
		int limit = 5;
		String uri = "/api/tables/"+ tableId +"/current_table_schemas?page="+ page + "&limit=" + limit;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(status, 200);
	}
	
	@Test
	public void getCurrentTableDetail() throws Exception {
		int currentTableDetailId = 1;
		String uri = "/api/current_table_schemas/" + currentTableDetailId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(status, 200);
	}
}
