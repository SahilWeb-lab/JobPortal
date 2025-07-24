package com.project.service.impl;

import java.util.List;

import javax.swing.SortOrder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.dto.UserRequest;
import com.project.dto.UserResponse;
import com.project.model.User;
import com.project.repository.UserRepository;
import com.project.response.PageResponse;
import com.project.service.UserService;
import com.project.validation.PageValidation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PageValidation pageValidation;
	
	@Override
	public Boolean createUser(UserRequest userRequest) {
		User user = modelMapper.map(userRequest, User.class);
		User saveUser = userRepository.save(user);
		
		return (ObjectUtils.isEmpty(saveUser)) ? false : true;
	}

	@Override
	public PageResponse<?> getAllUser(Integer pageNo, Integer pageSize,String sortOrder, String sortByFeild) {
		
//		Validate Page Request:
		pageValidation.validate(pageNo, pageSize);
		
//		Sorting Condition:
		Direction direction = (sortOrder.toUpperCase().equals("DESC")) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(direction, sortByFeild));
		Page<User> userPage = userRepository.findAll(pageable);
		
		PageResponse pageResponse = modelMapper.map(userPage, PageResponse.class);
		pageResponse.setPageNumber(pageResponse.getPageNumber() + 1);
		return pageResponse;
	}

}
