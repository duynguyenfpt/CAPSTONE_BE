package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.JobConvertor;
import com.web_service.dto.JobDTO;
import com.web_service.entity.JobEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestJobConverter {
	private JobEntity jobEntity;
	
	private JobDTO jobDTO;
	
	@InjectMocks
    private static JobConvertor jobConvertor = new JobConvertor();
	
	@Before
	public void setUp() {
		jobEntity = new JobEntity();
		@SuppressWarnings("deprecation")
		Long id = new Long(1);
		
		jobEntity.setId(id);
		jobEntity.setActive(true);
		jobEntity.setMaxRetries(10);
		jobEntity.setStatus("pending");
		jobEntity.setNumberRetries(5);
		
		jobDTO = new JobDTO();
		jobDTO.setActive(true);
		jobDTO.setMaxRetries(10);
		jobDTO.setStatus("pending");
		jobDTO.setNumberRetries(5);
	}
	
	@Test
	public void testToDTO() {
		
		JobDTO jobDTOResult = jobConvertor.toDTO(jobEntity);
		
		assertTrue(jobDTOResult.getId() == (long)1 
				&& jobDTOResult.getMaxRetries() == 10
				&& jobDTOResult.getNumberRetries() == 5
				&& jobDTOResult.isActive() == true);
	}
	
	@Test
	public void testToEntity() {
		
		JobEntity jobEntityResult = jobConvertor.toEntity(jobDTO);
		
		assertTrue(jobEntityResult.getMaxRetries() == 10
				&& jobEntityResult.getNumberRetries() == 5
				&& jobEntityResult.isActive() == true);
	}
	
	@Test
	public void updateEntity() {
		
		JobEntity jobEntityResult = jobConvertor.toEntity(jobDTO, jobEntity);
		
		assertTrue(jobEntityResult.getId() == (long)1 
				&& jobEntityResult.getMaxRetries() == 10
				&& jobEntityResult.getNumberRetries() == 5
				&& jobEntityResult.isActive() == true);
	}
}
