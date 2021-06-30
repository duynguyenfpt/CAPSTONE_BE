package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.entity.CurrentTableSchemaEntity;
import com.web_service.repository.CurrentTableSchemaRepository;

public class CurrentTableSchemaAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Autowired
	private CurrentTableSchemaRepository currentTableSchemaRepository;
	
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
		long currentTableDetailId = 1;
		CurrentTableSchemaEntity currentTableSchemaEntity = currentTableSchemaRepository.findOne(currentTableDetailId);
		String uri = "/api/current_table_schemas/" + currentTableDetailId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		if(currentTableSchemaEntity != null) {
			assertEquals(status, 200);
		}else {
			assertEquals(status, 404);
		}
		
	}
}
