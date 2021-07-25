package com.web_service.services.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.JobConvertor;
import com.web_service.dto.JobDTO;
import com.web_service.dto.JobDetailDTO;
import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.JobEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.SchemaChangeHistoryEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.IJobService;

@Service
public class JobService implements IJobService{
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobConvertor jobConvertor;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<JobDTO> findAll(Pageable pageable) {
		List<JobDTO> results = new ArrayList<>();
		List<JobEntity> entities = jobRepository.findAll(pageable).getContent();
		for (JobEntity item: entities) {
			JobDTO jobDTO = jobConvertor.toDTO(item);
			results.add(jobDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) jobRepository.count();
	}

	@Override
	public JobDTO getById(long id) {
		JobEntity jobEntity = jobRepository.findOne(id);
		JobDTO jobDTO = jobConvertor.toDTO(jobEntity);
		return jobDTO;
	}

	@Override
	public JobDTO save(JobDTO jobDTO) {
		JobEntity jobEntity = new JobEntity();
		if (jobDTO.getId() != null) {
			JobEntity oldJobEntity = jobRepository.findOne(jobDTO.getId());
			jobEntity = jobConvertor.toEntity(jobDTO, oldJobEntity);
		} else {
			jobEntity = jobConvertor.toEntity(jobDTO);
		}
		
		AccountEntity executedBy =  accountRepository.findOne(jobDTO.getExecutedById());
		jobEntity.setExecutedBy(executedBy);
		
		RequestEntity requestEntity = requestRepository.findOne(jobDTO.getRequestId());
		jobEntity.setRequest(requestEntity);
			
		jobEntity = jobRepository.save(jobEntity);
		return jobConvertor.toDTO(jobEntity);
	}

	@Override
	public void delete(long id) {
		jobRepository.delete(id);
	}
	
	@Override
	public List<JobDetailDTO> getJobDetails(long jobId, int page, int limit) {
		String query = "SELECT si.server_domain,si.server_host,di.`port`,di.database_type,str.identity_id,str.partition_by,str.id as str_id, jobs.id as job_id,\n" +
                "jobs.max_retries, jobs.number_retries, tm.latest_offset, tm.`table`, tm.database, jobs.created_date FROM\n" +
                "webservice_test.database_infos di\n" +
                "inner join \n" +
                "(select * from webservice_test.`tables`)tbls\n" +
                "inner join webservice_test.server_infos si\n" +
                "inner join webservice_test.sync_table_requests as str\n" +
                "inner join webservice_test.request \n" +
                "inner join webservice_test.jobs\n" +
                "inner join cdc.table_monitor as tm\n" +
                "on di.id = tbls.database_info_id\n" +
                "and di.server_info_id = si.id\n" +
                "and str.table_id = tbls.id\n" +
                "and str.request_id = request.id\n" +
                "and jobs.request_id = request.id\n" +
                "and (si.server_domain = tm.`host` or si.server_host = tm.`host`)\n" +
                "and di.`port` = tm.`port`\n" +
                "and tm.is_active = 1 and tm.is_ready = 1 " +
                "and tm.database = di.database_name " +
                "and tm.table = tbls.table_name " +
                "and number_retries < max_retries and jobs.deleted = 0\n" +
                "and jobs.id = " + jobId + "\n" +
                "order by created_date DESC";

		@SuppressWarnings("unchecked")
		List<Object[]> results = em
				.createNativeQuery(query)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.getResultList();

		List<JobDetailDTO> jobDetails = new ArrayList<JobDetailDTO>();
		results.stream().forEach((record) -> {
			JobDetailDTO jobDetailDTO = new JobDetailDTO();
			jobDetailDTO.setServerDomain((String) record[0]);
			jobDetailDTO.setServerHost((String) record[1]);
			jobDetailDTO.setPort((String) record[2]);
			jobDetailDTO.setDatabaseType((String) record[3]);
			jobDetailDTO.setIdentityId((String) record[4]);
			jobDetailDTO.setPartitionBy((String) record[5]);
			jobDetailDTO.setStrId((BigInteger) record[6]);
			jobDetailDTO.setJobId((BigInteger) record[7]);
			jobDetailDTO.setMaxRetries((Integer) record[8]);
			jobDetailDTO.setNumberRetries((Integer) record[9]);
			jobDetailDTO.setLastestOffset((Integer) record[10]);
			jobDetailDTO.setTable((String) record[11]);
			jobDetailDTO.setDatabase((String) record[12]);
			jobDetailDTO.setCreatedDate(String.valueOf(((Timestamp) record[13]).getTime()));
			
			jobDetails.add(jobDetailDTO);
		});
		
		return jobDetails;
	}

	@Override
	public int totalItemJobDetails(long jobId) {
		String query = "SELECT si.server_domain,si.server_host,di.`port`,di.database_type,str.identity_id,str.partition_by,str.id as str_id, jobs.id as job_id,\n" +
                "jobs.max_retries, jobs.number_retries, tm.latest_offset, tm.`table`, tm.database, jobs.created_date FROM\n" +
                "webservice_test.database_infos di\n" +
                "inner join \n" +
                "(select * from webservice_test.`tables`)tbls\n" +
                "inner join webservice_test.server_infos si\n" +
                "inner join webservice_test.sync_table_requests as str\n" +
                "inner join webservice_test.request \n" +
                "inner join webservice_test.jobs\n" +
                "inner join cdc.table_monitor as tm\n" +
                "on di.id = tbls.database_info_id\n" +
                "and di.server_info_id = si.id\n" +
                "and str.table_id = tbls.id\n" +
                "and str.request_id = request.id\n" +
                "and jobs.request_id = request.id\n" +
                "and (si.server_domain = tm.`host` or si.server_host = tm.`host`)\n" +
                "and di.`port` = tm.`port`\n" +
                "and tm.is_active = 1 and tm.is_ready = 1 " +
                "and tm.database = di.database_name " +
                "and tm.table = tbls.table_name " +
                "and number_retries < max_retries and jobs.deleted = 0\n" +
                "and jobs.id = " + jobId + "\n" +
                "order by created_date DESC";

		@SuppressWarnings("unchecked")
		List<Object[]> results = em
				.createNativeQuery(query)
				.getResultList();
		
		return results.size();
	}
	
	
	
}
