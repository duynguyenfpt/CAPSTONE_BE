package com.web_service.services;

import com.web_service.dto.UserDTO;


public interface IUserService {
	UserDTO save(UserDTO newDTO);
	void delete(long[] ids);
	int totalItem();
	UserDTO getById(long id);
}
