package com.web_service.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.web_service.dto.ColumnProperty;
import com.web_service.entity.DatabaseInfoEntity;

public class SqlUtils {
	public static Connection getConnectionFromHostAndDB(DatabaseInfoEntity databaseInfoEntity)
			throws IOException, SQLException {
		Connection conn = null;
		if (databaseInfoEntity.getDatabaseType().equals("mysql")) {
			 conn = getConnection(getConnectionMySqlString(databaseInfoEntity.getServerInfo().getServerHost(),
					databaseInfoEntity.getPort(), databaseInfoEntity.getDatabaseName(),
					databaseInfoEntity.getUsername() , databaseInfoEntity.getPassword()));
		} else if (databaseInfoEntity.getDatabaseType().equals("postgresql")) {
			
			conn = getConnectionPostgresql(databaseInfoEntity.getServerInfo().getServerHost(),
					databaseInfoEntity.getPort(), databaseInfoEntity.getDatabaseName(),
					databaseInfoEntity.getUsername(), databaseInfoEntity.getPassword());
		} else if (databaseInfoEntity.getDatabaseType().equals("oracle")) {

		}
		return conn;
	}

	
	public static String getConnectionString(String host, String port, String db, String userName, String password) {
      return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&useSSL=false&characterEncoding=utf-8", host, port, db, userName, password);
    }
	
	public static Connection getConnection(String dbURL) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
	
	public static String getConnectionMySqlString(String host, String port, String db, String userName,
			String password) {
		return String.format(
				"jdbc:mysql://%s:%s/%s?user=%s&password=%s&useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=UTC",
				host, port, db, userName, password);
	}

    public static List<String> listTable(Connection connection, String db, String dbType) throws SQLException {
        ArrayList<String> listTables = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {
            listTables.add(rs.getString(String.format("Tables_in_%s", db)));
        }
        connection.close();
        return listTables;
        
    }
	
	public static Connection getConnectionPostgresql(String host, String port, String dbName, String username, String password) {
        Connection conn = null;
        try {
            String URL = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
            conn = DriverManager.getConnection(URL, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
		
	
	public static List<String> getListTable(String db, Connection connection) throws SQLException, IOException {
        ArrayList<String> listTables = new ArrayList<>();
        String searchQuery = "select distinct table_name from table_schema_detail where db_type = ?";
        PreparedStatement stmt = connection.prepareStatement(searchQuery);
        stmt.setString(1, db);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            listTables.add(rs.getString(String.format("table_name", db)));
        }
        return listTables;
    }
	
	public static void bashInsertListTableSchema(Connection connection, List<String> listTables, String dbType, Connection connectionToDas) throws SQLException, IOException {
        String sql = "insert into table_schema_detail(db_type,table_name,table_schema) values (?,?,?)";
        PreparedStatement prpStmt = connectionToDas.prepareStatement(sql);
        int index = 0;
        for (String tableName : listTables) {
            String schema = getTableSchema(connection, tableName);
            if (schema != null) {
                prpStmt.setString(1, dbType);
                prpStmt.setString(2, tableName);
                prpStmt.setString(3, schema);
                prpStmt.addBatch();
                index++;
                // bash 1000 query each time
                if (index % 1000 == 0 || index == listTables.size()) {
                    prpStmt.executeBatch();
                }
            }
        }
    }
	
	public static String getTableSchema(Connection connection, String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs;
        try {
            rs = stmt.executeQuery(String.format("DESCRIBE `%s`", tableName));
        } catch (Exception exception) {
            return null;
        }
        ArrayList<ColumnProperty> listCol = new ArrayList<>();
        while (rs.next()) {
            ColumnProperty cp = new ColumnProperty();
            String field = rs.getString("field");
            cp.setField(field);
            String type = rs.getString("type");
            if (type.contains("(")) {
                /*
                 * if data type looks like varchar(523)
                 * then extract 523
                 * */
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(type);
                if (matcher.find()) {
                    cp.setTypeSize(Integer.parseInt(matcher.group(0)));
                } else {
                    cp.setTypeSize(null);
                }
                cp.setType(type.substring(0, type.indexOf("(")));
            } else {
                cp.setType(type);
            }

            if (type.endsWith(" signed")) {
                /*
                 * if data type looks like int(11) unsigned or int(10) signed
                 * then extract unsigned or signed
                 * */
                cp.setSigned(true);
            } else if (type.endsWith(" unsigned")) {
                cp.setSigned(false);
            } else {
                cp.setSigned(null);
            }
            ArrayList<String> possibleValues = null;
            if (type.startsWith("enum")) {
                Pattern pattern = Pattern.compile("'((\\w)+)'");
                Matcher matcher = pattern.matcher(type);
                possibleValues = new ArrayList<>();
                while (matcher.find()) {
                    possibleValues.add(matcher.group(1));
                }
                cp.setPossibleValues(possibleValues);
            }
            cp.setDefaultValue(rs.getString("default"));
            listCol.add(cp);
        }
        rs.close();
        stmt.close();
        // sort according to field
        Collections.sort(listCol);
        Gson gson = new Gson();
        return gson.toJson(listCol);
    }
}
