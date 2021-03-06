package com.web_service.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.web_service.converter.JobConvertor;
import com.web_service.dto.JobDTO;
import com.web_service.dto.JobDetailDTO;
import com.web_service.dto.MergeRequestMapper;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.AddColumnTableRequestEntity;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ETLEntity;
import com.web_service.entity.JobEntity;
import com.web_service.entity.MergeRequestEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.entity.SyncTableRequestEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.AddColumnTableRequestRepository;
import com.web_service.repository.ETLRequestRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.MergeRequestRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.repository.SyncTableRequestRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.IJobService;

@Service
public class JobService implements IJobService {
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobConvertor jobConvertor;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private SyncTableRequestRepository syncTableRequestRepository;

	@Autowired
	private AddColumnTableRequestRepository addColumnTableRequestRepository;

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private MergeRequestRepository mergeRequestRepository;
	
	@Autowired
	private ETLRequestRepository etlRequestRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<JobDTO> findAll(String keyword, Pageable pageable) {
		List<JobDTO> results = new ArrayList<>();
		//get all job
		List<JobEntity> entities = jobRepository.getAllJob(keyword, pageable).getContent();
		for (JobEntity item : entities) {
			JobDTO jobDTO = jobConvertor.toDTO(item);
			results.add(jobDTO);
		}
		return results;
	}

	@Override
	public int totalItem(String keyword) {
		return (int) jobRepository.countJob(keyword);
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
			//update job
			JobEntity oldJobEntity = jobRepository.findOne(jobDTO.getId());
			jobEntity = jobConvertor.toEntity(jobDTO, oldJobEntity);
		} else {
			//create job
			jobEntity = jobConvertor.toEntity(jobDTO);
		}

		AccountEntity executedBy = accountRepository.findOne(jobDTO.getExecutedById());
		jobEntity.setExecutedBy(executedBy);

		RequestEntity requestEntity = requestRepository.findOne(jobDTO.getRequestId());
		jobEntity.setRequest(requestEntity);

		//create job
		jobEntity = jobRepository.save(jobEntity);
		return jobConvertor.toDTO(jobEntity);
	}

	@Override
	public void delete(long id) {
		jobRepository.delete(id);
	}

	@Override
	public List<JobDetailDTO> getJobDetail(long jobId) {
		List<JobDetailDTO> listJobDetailDTO = new ArrayList<>();
		try {
			JobEntity jobEntity = jobRepository.findOne(jobId);
			RequestEntity requestEntity = jobEntity.getRequest();


			if (requestEntity.getRequestType().toLowerCase().trim().equals("synctable")) {
				//get job detail of synctable request
				List<SyncTableRequestEntity> syncTableRequestEntities = syncTableRequestRepository
						.findByRequestId(requestEntity.getId());
				long tableId = syncTableRequestEntities.get(0).getTableInfo().getId();
				JobDetailDTO jobDetail = getJobDetail(tableId, jobEntity);
				listJobDetailDTO.add(jobDetail);

			} else if (requestEntity.getRequestType().toLowerCase().trim().equals("mergerequest")) {
				//get job detail of merge request
				MergeRequestEntity mergeRequestEntity = mergeRequestRepository.findByRequestId(requestEntity.getId());

				String metaData = "";
				if (mergeRequestEntity.getCurrentMetadata() == null || mergeRequestEntity.getCurrentMetadata().isEmpty()) {
					metaData = mergeRequestEntity.getLatestMetadata();
				} else {
					metaData = mergeRequestEntity.getCurrentMetadata();
				}

				Gson gson = new Gson();
				//parse string json to map
				Map mergeRequestMapper = gson.fromJson(metaData, Map.class);
				ArrayList<Map> list = (ArrayList<Map>) mergeRequestMapper.get("list_tables");
				for (Map object : list) {
					long tableIdResult = new Double((double) object.get("table_id")).longValue();

					JobDetailDTO jobDetail = getJobDetail(tableIdResult, jobEntity);
					listJobDetailDTO.add(jobDetail);
				}
			} else if (requestEntity.getRequestType().toLowerCase().trim().equals("etlrequest")) {
				//get job detail of etl request
				ETLEntity etlEntity = etlRequestRepository.findByRequestId(requestEntity.getId());
				listJobDetailDTO.add(getJobDetailETLRequest(jobEntity, etlEntity));
			}
			return listJobDetailDTO;
		}catch (Exception e) {
			return listJobDetailDTO;
		}
		
	}

	private JobDetailDTO getJobDetail(long tableId, JobEntity jobEntity) {
		JobDetailDTO jobDetailDTO = new JobDetailDTO();
		TableEntity tableEntity = tableRepository.findOne(tableId);
		DatabaseInfoEntity databaseInfoEntity = tableEntity.getDatabaseInfo();
		ServerInfoEntity serverInfoEntity = databaseInfoEntity.getServerInfo();
		AccountEntity executedBy = jobEntity.getExecutedBy();

		jobDetailDTO.setServerDomain(serverInfoEntity.getServerDomain());
		jobDetailDTO.setServerHost(serverInfoEntity.getServerHost());
		jobDetailDTO.setPort(databaseInfoEntity.getPort());
		jobDetailDTO.setDatabaseType(databaseInfoEntity.getDatabaseType());
		jobDetailDTO.setMaxRetries(jobEntity.getMaxRetries());
		jobDetailDTO.setNumberRetries(jobEntity.getNumberRetries());
		jobDetailDTO.setTable(tableEntity.getTableName());
		jobDetailDTO.setDatabase(databaseInfoEntity.getDatabaseName());
		jobDetailDTO.setCreatedDate(String.valueOf(jobEntity.getCreatedDate()));
		jobDetailDTO.setActive(jobEntity.isActive());
		jobDetailDTO.setExecutedBy(executedBy.getUsername());
		jobDetailDTO.setCreatedBy(jobEntity.getCreatedBy());
		jobDetailDTO.setRequestId(jobEntity.getRequest().getId());

		return jobDetailDTO;
	}
	
	private JobDetailDTO getJobDetailETLRequest(JobEntity jobEntity, ETLEntity etlEntity) {
		JobDetailDTO jobDetailDTO = new JobDetailDTO();
		AccountEntity executedBy = jobEntity.getExecutedBy();

		jobDetailDTO.setServerDomain("");
		jobDetailDTO.setServerHost("");
		jobDetailDTO.setPort("");
		jobDetailDTO.setDatabaseType("");
		jobDetailDTO.setMaxRetries(jobEntity.getMaxRetries());
		jobDetailDTO.setNumberRetries(jobEntity.getNumberRetries());
		jobDetailDTO.setTable("");
		jobDetailDTO.setDatabase("");
		jobDetailDTO.setCreatedDate(String.valueOf(jobEntity.getCreatedDate()));
		jobDetailDTO.setActive(jobEntity.isActive());
		jobDetailDTO.setExecutedBy(executedBy.getUsername());
		jobDetailDTO.setCreatedBy(jobEntity.getCreatedBy());
		jobDetailDTO.setRequestId(jobEntity.getRequest().getId());
		jobDetailDTO.setQuery(etlEntity.getQuery());

		return jobDetailDTO;
	}

	@Override
	public int totalItemJobDetails(long jobId) {
		String query = "SELECT si.server_domain,si.server_host,di.`port`,di.database_type,str.identity_id,str.partition_by,str.id as str_id, jobs.id as job_id,\n"
				+ "jobs.max_retries, jobs.number_retries, tm.latest_offset, tm.`table`, tm.database, jobs.created_date, jobs.request_id FROM\n"
				+ "webservice_test.database_infos di\n" + "inner join \n"
				+ "(select * from webservice_test.`tables`)tbls\n" + "inner join webservice_test.server_infos si\n"
				+ "inner join webservice_test.sync_table_requests as str\n" + "inner join webservice_test.request \n"
				+ "inner join webservice_test.jobs \n" + "inner join cdc.table_monitor as tm\n"
				+ "on di.id = tbls.database_info_id\n" + "and di.server_info_id = si.id\n"
				+ "and str.table_id = tbls.id\n" + "and str.request_id = request.id\n"
				+ "and jobs.request_id = request.id\n"
				+ "and (si.server_domain = tm.`host` or si.server_host = tm.`host`)\n" + "and di.`port` = tm.`port`\n"
				+ "and tm.is_active = 1 and tm.is_ready = 1 " + "and tm.database = di.database_name "
				+ "and tm.table = tbls.table_name " + "and number_retries < max_retries and jobs.deleted = 0\n"
				+ "and jobs.id = " + jobId + "\n" + "order by created_date DESC";

		@SuppressWarnings("unchecked")
		List<Object[]> results = em.createNativeQuery(query).getResultList();

		return results.size();
	}

}
