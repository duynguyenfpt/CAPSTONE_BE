package com.web_service.services;

import java.sql.Connection;

public interface IChangeSchemaDetectionService {
	void checkSchema(String host, String databaseType, String databaseName, String key, Connection schemaConnection);
}
