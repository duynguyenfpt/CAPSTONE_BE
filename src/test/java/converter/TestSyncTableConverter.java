package converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.web_service.converter.SyncTableConverter;
import com.web_service.dto.SyncTableRequestDTO;
import com.web_service.entity.SyncTableRequestEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestSyncTableConverter {
	private SyncTableRequestEntity syncTableRequestEntity;
	
	private SyncTableRequestDTO syncTableRequestDTO;

	
	@InjectMocks
    private static SyncTableConverter syncTableConverter = new SyncTableConverter();
	
	@Before
	public void setUp() {
		syncTableRequestEntity = new SyncTableRequestEntity();
		Long id = new Long(1);
		
		syncTableRequestEntity.setId(id);
		syncTableRequestEntity.setIsAll(true);
		syncTableRequestEntity.setPartitionBy("request_date");
		syncTableRequestEntity.setIdentityId("request_id");
		
		syncTableRequestDTO = new SyncTableRequestDTO();
		syncTableRequestDTO.setIsAll(true);
		syncTableRequestDTO.setPartitionBy("request_date");
		syncTableRequestDTO.setIdentityId("request_id");
	}
	
	@Test
	public void testToDTO() {
		
		SyncTableRequestDTO syncTableRequestDTOResult = syncTableConverter.toDTO(syncTableRequestEntity);
		
		assertTrue(syncTableRequestDTOResult.getId() == (long)1 
				&& syncTableRequestDTOResult.isAll() == true
				&& syncTableRequestDTOResult.getPartitionBy().equals("request_date")
				&& syncTableRequestDTOResult.getIdentityId().equals("request_id"));
	}
	
	@Test
	public void testToEntity() {
		
		SyncTableRequestEntity syncTableRequestEntityResult = syncTableConverter.toEntity(syncTableRequestDTO);
		
		assertTrue(syncTableRequestEntityResult.isAll() == true
				&& syncTableRequestEntityResult.getPartitionBy().equals("request_date")
				&& syncTableRequestEntityResult.getIdentityId().equals("request_id"));
	}
	
	@Test
	public void updateEntity() {
		
		SyncTableRequestEntity syncTableRequestEntityResult = syncTableConverter.toEntity(syncTableRequestDTO, syncTableRequestEntity);
		
		assertTrue(syncTableRequestEntityResult.isAll() == true
				&& syncTableRequestEntityResult.getPartitionBy().equals("request_date")
				&& syncTableRequestEntityResult.getIdentityId().equals("request_id"));
	}
}
