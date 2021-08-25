package com.web_service.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MergeRequestMapper {

		@JsonProperty("table_id")
		public int tableId;
		
		@JsonProperty("table")
		public String table;
		
		@JsonProperty("database_alias")
		public String databaseAlias;
}
