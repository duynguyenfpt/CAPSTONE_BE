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
		String databaseType = databaseInfoEntity.getDatabaseType().trim();
		ArrayList<String> result = new ArrayList<>();
		if(databaseType.equals("mysql")) {
			Connection connection = getConnectionMySql(getConnectionMySqlString(serverInfoEntity.getServerHost(),
					databaseInfoEntity.getPort(), databaseInfoEntity.getDatabaseName(),
					databaseInfoEntity.getUsername() , databaseInfoEntity.getPassword()));
			
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
		}else if(databaseType.equals("postgresql")) {
			Connection connection = getConnectionPostgreSql(getConnectionPostgreSqlString(serverInfoEntity.getServerHost(),
					databaseInfoEntity.getPort(), databaseInfoEntity.getDatabaseName(),
					databaseInfoEntity.getUsername() , databaseInfoEntity.getPassword()));
			
			try {
				Statement statement = connection.createStatement();
				String[] schemaTable = tableEntity.getTableName().split("[.]");
				ResultSet rs = statement.executeQuery(String.format("select column_name from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME = '%s';", 
						schemaTable[0], schemaTable[1]));
				
				while (rs.next()) {
					result.add(rs.getString("column_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (databaseType.equals("oracal")) {
			
			Connection connection = getConnectionOracle(getConnectionOracleString(databaseInfoEntity.getUsername(),
					databaseInfoEntity.getPassword(), serverInfoEntity.getServerHost(),
					databaseInfoEntity.getPort(), databaseInfoEntity.getSid()));
			
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(String.format("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME = '%s';", 
						tableEntity.getTableName()));
				
				while (rs.next()) {
					result.add(rs.getString("COLUMN_NAME"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static String getConnectionMySqlString(String host, String port, String db, String userName,
			String password) {
		return String.format(
				"jdbc:mysql://%s:%s/%s?user=%s&password=%s&useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=UTC",
				host, port, db, userName, password);
	}

	public static String getConnectionPostgreSqlString(String host, String port, String db, String userName,
			String password) {
		return String.format(
				"jdbc:postgresql://%s:%s/%s?user=%s&password=%s&ssl=false",
				host, port, db, userName, password);
	}
	
	public static String getConnectionOracleString(String host, String port, String username,
			String password, String sid) {
		return String.format(
				"jdbc:oracle:thin:%s/%s@%s:%s:%s", username, password, host, port, sid);
	}
	
//	public static Connection getConnectionOracle(String username, String password, String host, String port, String SID) {
//        SID = "oracle";
//        Connection conn = null;
//        try {
////            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection("jdbc:oracle:thin:system/capstone@localhost:1521:oracle");
////            conn = DriverManager.getConnection(String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, SID), username, password);
//            System.out.println("connected successfully");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return conn;
//    }
	
	public static Connection getConnectionMySql(String dbURL) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
	
	public static Connection getConnectionPostgreSql(String dbURL) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	    return conn;
	}
	
	public static Connection getConnectionOracle(String dbURL) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	    return conn;
	}
}
