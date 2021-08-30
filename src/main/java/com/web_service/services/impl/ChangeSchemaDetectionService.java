package com.web_service.services.impl;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.repository.DatabaseInfoRepository;
import com.web_service.services.IChangeSchemaDetectionService;
import com.web_service.utils.SqlUtils;
import com.web_service.utils.Utils;

@Service
public class ChangeSchemaDetectionService implements IChangeSchemaDetectionService {
	@Autowired
	DatabaseInfoRepository databaseInfoRepository;

	@Override
	public void checkSchema(String host, String databaseType, String databaseName, String key,
			Connection schemaConnection) {
		try {
			DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(139L);
			Connection connection = SqlUtils.getConnectionFromHostAndDB(databaseInfoEntity);
			if (connection != null) {
				List<String> listTables = SqlUtils.listTable(connection, databaseInfoEntity.getDatabaseName(), databaseInfoEntity.getDatabaseType());
				List<String> listTableFromDBSchema = SqlUtils.getListTable(databaseType, schemaConnection);
				List<List<String>> compareTables = Utils.compareDiff(listTables, listTableFromDBSchema);
				System.out.println("number common tables: " + compareTables.get(0).size());
				System.out.println("number different tables: " + compareTables.get(1).size());
				SqlUtils.bashInsertListTableSchema(connection, compareTables.get(1), databaseType, schemaConnection);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
