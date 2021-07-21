package com.web_service.services;

import java.util.List;

public interface IColumnService {
	List<String> getColumnByTable(long tableId);
}
