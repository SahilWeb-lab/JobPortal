package com.project.service;

import com.project.dto.UserRequest;
import com.project.response.PageResponse;

public interface UserService {

//	Create a method to save/create new user:
	public Boolean createUser(UserRequest userRequest);
	
//	Create a method to get all user:
	public PageResponse<?> getAllUser(Integer pageNo, Integer pageSize,String sortOrder, String sortByFeild);
	
}
