package com.web_service.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.dto.ColumnDTO;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.DatabaseInfoRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.IColumnService;

@Service
public class ColumnService implements IColumnService{
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	DatabaseInfoRepository databaseInfoRepository;
	
	@Override
	public ArrayList<String> getColumnByTable(long tableId) {
		TableEntity tableEntity = tableRepository.findOne(tableId);
		DatabaseInfoEntity databaseInfoEntity = tableEntity.getDatabaseInfo();
		ServerInfoEntity serverInfoEntity = databaseInfoEntity.getServerInfo();
		Connection connection = getConnection(getConnectionString(serverInfoEntity.getServerHost(),
				databaseInfoEntity.getPort(), databaseInfoEntity.getDatabaseName(),
				databaseInfoEntity.getUsername() , databaseInfoEntity.getPassword()));
		ArrayList<String> result = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(String.format("describe %s.%s;", 
					databaseInfoEntity.getDatabaseName(), tableEntity.getTableName()));
			
			while (rs.next()) {
				result.add(rs.getString("Field"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getConnectionString(String host, String port, String db, String userName, String password) {
       return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=UTC", host, port, db, userName, password);
    }
	
	public static Connection getConnection(String dbURL) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
