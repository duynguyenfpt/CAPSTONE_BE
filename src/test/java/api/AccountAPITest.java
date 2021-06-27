package api;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AccountAPITest extends AbstractTest{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAccountsList() throws Exception {
		String uri = "/api/accounts?page=1&limit=5";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertTrue(status == 200);
	}
}
