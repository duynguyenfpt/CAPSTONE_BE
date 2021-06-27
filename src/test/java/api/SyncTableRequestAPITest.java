package api;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.SyncTableRequestDTO;

public class SyncTableRequestAPITest extends AbstractTest{
	@Test
	public void createSyncTableRequest() throws Exception {
		String uri = "/api/sync_table_requests";
		SyncTableRequestDTO syncTableRequestDTO = new SyncTableRequestDTO();
		long tableId = 1;
		long requestId = 1;
		boolean isAll = true;
		Date fromDate = Date.valueOf("2020-12-12");
		Date toDate = Date.valueOf("2021-12-12");
		Time timeRequest = Time.valueOf("22:11:11");
		
		syncTableRequestDTO.setTableId(tableId);
		syncTableRequestDTO.setRequestId(requestId);
		syncTableRequestDTO.setIsAll(isAll);
		syncTableRequestDTO.setFromDate(fromDate);
		syncTableRequestDTO.setToDate(toDate);
		syncTableRequestDTO.setTimeRequest(timeRequest);
		
		String inputJson = super.mapToJson(syncTableRequestDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
}
