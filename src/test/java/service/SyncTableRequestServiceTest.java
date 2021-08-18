package service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.web_service.converter.SyncTableConverter;
import com.web_service.dto.SyncTableRequestDTO;
import com.web_service.entity.SyncTableRequestEntity;
import com.web_service.repository.SyncTableRequestRepository;
import com.web_service.services.impl.SyncTableRequestService;

@RunWith(MockitoJUnitRunner.class)
public class SyncTableRequestServiceTest {
	@InjectMocks
    private static SyncTableRequestService syncTableRequestService = new SyncTableRequestService();
		
	@Mock
    private static SyncTableConverter syncTableConverter;
	
	@Mock
    private static SyncTableRequestRepository syncTableRequestRepository;
	
	private SyncTableRequestEntity syncTableRequestEntity;
	
	private SyncTableRequestDTO syncTableRequestDTO;
		
	@Before
    public void setup() {
		Long id = new Long(1);
		syncTableRequestEntity = new SyncTableRequestEntity();
		syncTableRequestEntity.setId(id);
		syncTableRequestEntity.setIsAll(true);
		syncTableRequestEntity.setMessage("success");
		
		syncTableRequestDTO = new SyncTableRequestDTO();
		syncTableRequestDTO.setId(id);
		syncTableRequestDTO.setIsAll(true);
		syncTableRequestDTO.setMessage("success");
    }
	
	
	@Test
	public void findAllServerInfo() {
		int page = 0;
		int limit = 1;
		
        Pageable pageable = new PageRequest(page, limit);
        Page<SyncTableRequestEntity> syncTableRequestEntityPage = new PageImpl<>(Collections.singletonList(syncTableRequestEntity));
        Mockito.when(syncTableRequestRepository.findAll(pageable)).thenReturn(syncTableRequestEntityPage);
        Page<SyncTableRequestEntity> syncPage = syncTableRequestRepository.findAll(pageable);
        assertEquals(syncPage.getNumberOfElements(), 1);
	}
}
