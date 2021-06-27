package api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.dto.AddColumnTableRequestDTO;

public class AddColumnTableRequestAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAccountsList() throws Exception {
		String uri = "/api/add_column_table_request";
		AddColumnTableRequestDTO addColumnTableRequestDTO = new AddColumnTableRequestDTO();
		long[] rowIds = {1,2,3};
		int resquestTypeId = 1;
		int tableId = 1;
		
		addColumnTableRequestDTO.setRowIds(rowIds);
		addColumnTableRequestDTO.setRequestTypeId(resquestTypeId);
		addColumnTableRequestDTO.setTableId(tableId);
		
		String inputJson = super.mapToJson(addColumnTableRequestDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
}
