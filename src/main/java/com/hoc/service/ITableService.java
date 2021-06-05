package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.NewDTO;
import com.hoc.dto.TableDTO;

public interface ITableService {
	TableDTO save(TableDTO tableDTO);
	void delete(long[] ids);
	List<TableDTO> findAll(Pageable pageable);
	int totalItem();
	TableDTO getById(long id);
}
