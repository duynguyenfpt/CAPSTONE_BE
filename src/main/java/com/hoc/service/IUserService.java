package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.UserDTO;


public interface IUserService {
	UserDTO save(UserDTO newDTO);
	void delete(long[] ids);
	int totalItem();
	UserDTO getById(long id);
}
