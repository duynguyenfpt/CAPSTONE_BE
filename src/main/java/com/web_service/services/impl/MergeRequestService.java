package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.MergeRequestConverter;
import com.web_service.dto.MergeRequestDTO;
import com.web_service.entity.MergeRequestEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.repository.MergeRequestRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.IMergeRequestService;

@Service
public class MergeRequestService implements IMergeRequestService{
	@Autowired
	MergeRequestRepository mergeRequestRepository;
	
	@Autowired
	MergeRequestConverter mergeRequestConverter;
	
	@Autowired
	RequestRepository requestRepository;

	@Override
	@Transactional
	public MergeRequestDTO save(MergeRequestDTO mergeRequestDTO) {
		MergeRequestEntity mergeRequestEntity = new MergeRequestEntity();
		
		//create request
		RequestEntity requestEntity = new RequestEntity();
		requestEntity.setRequestType("MergeRequest");
		requestEntity = requestRepository.save(requestEntity);
		
		//create merge request
		mergeRequestEntity = mergeRequestConverter.toEntity(mergeRequestDTO);
		mergeRequestEntity.setRequest(requestEntity);

		mergeRequestEntity = mergeRequestRepository.save(mergeRequestEntity);
		return mergeRequestConverter.toDTO(mergeRequestEntity);
	}
	
	@Override
	@Transactional
	public MergeRequestDTO update(MergeRequestDTO mergeRequestDTO) {
		//update merge request
		MergeRequestEntity mergeRequestEntity = new MergeRequestEntity();
		MergeRequestEntity oldMergeRequestEntity = mergeRequestRepository.findByRequestId(mergeRequestDTO.getRequestId());
		mergeRequestEntity = mergeRequestConverter.toEntity(mergeRequestDTO, oldMergeRequestEntity);
		
		mergeRequestEntity = mergeRequestRepository.save(mergeRequestEntity);
		return mergeRequestConverter.toDTO(mergeRequestEntity);
	}

	@Override
	public void delete(long id) {
		mergeRequestRepository.delete(id);
	}

	@Override
	public List<MergeRequestDTO> findAll(Pageable pageable) {
		List<MergeRequestDTO> results = new ArrayList<>();
		//get merge request from db
		List<MergeRequestEntity> entities;
		entities = mergeRequestRepository.findAll(pageable).getContent();
		
		for (MergeRequestEntity item: entities) {
			MergeRequestDTO mergeRequestDTO = mergeRequestConverter.toDTO(item);
			results.add(mergeRequestDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) mergeRequestRepository.count();
	}

	@Override
	public MergeRequestDTO getByRequestId(long requestId) {
		MergeRequestEntity mergeRequestEntity = mergeRequestRepository.findByRequestId(requestId);
		MergeRequestDTO mergeRequestDTO = mergeRequestConverter.toDTO(mergeRequestEntity);
		
		return mergeRequestDTO;
	}
//	public static Connection getConnectionFromHostAndDB(String host, String database, String key, Connection connection)
//			throws IOException, SQLException {
//		String getDBInfoQuery = "SELECT * FROM database_connect_detail WHERE db_host = ? and db_type = ? ";
//		PreparedStatement getInfoStmt = connection.prepareStatement(getDBInfoQuery);
//		getInfoStmt.setString(1, host);
//		getInfoStmt.setString(2, database);
//		ResultSet rs = getInfoStmt.executeQuery();
//		while (rs.next()) {
//			String port = rs.getString("db_port");
//			String username = rs.getString("db_username");
//			String password = rs.getString("db_password");
//			String db = rs.getString("db_name");
//			return SqlUtils.getConnection(SqlUtils.getConnectionString(host, port, db, username, password ));
//		}
//		return null;
//	}
//
//	private static void checkSchema(String host, String databaseType, String databaseName, String key,
//			Connection schemaConnection) throws Exception {
//		Connection connection = SqlUtils.getConnectionFromHostAndDB(host, databaseType, key, schemaConnection);
//		if (connection != null) {
//			List<String> listTables = SqlUtils.listTable(connection, databaseName);
//			List<String> listTableFromDBSchema = SqlUtils.getListTable(databaseType, schemaConnection);
//			List<List<String>> compareTables = Utils.compareDiff(listTables, listTableFromDBSchema);
////			SqlUtils.bashInsertListTableSchema(connection, compareTables.get(1), databaseType, schemaConnection);
////			SqlUtils.captureSchemaChange(connection, compareTables.get(0), databaseType, schemaConnection);
//		} else {
//			throw new Exception("THERE IS NO SUCH A DATABASE, PLEASE ENTER INFORMATION TO IMPORT DATA");
//		}
//	}
//
//	public static void checkAll(String schemaConnection, String key) throws Exception {
//		Connection connection = SqlUtils.getConnection(schemaConnection);
//		String getAllDBQuery = "SELECT * FROM database_connect_detail WHERE is_success = 1";
//		PreparedStatement prpStmt = connection.prepareStatement(getAllDBQuery);
//		ResultSet rs = prpStmt.executeQuery();
//		while (rs.next()) {
//			String host = rs.getString("db_host");
//			String dbType = rs.getString("db_type");
//			String dbName = rs.getString("db_name");
//			if (!dbType.equals("das")) {
//				checkSchema(host, dbType, dbName, key, connection);
//			}
//		}
//	}
}
