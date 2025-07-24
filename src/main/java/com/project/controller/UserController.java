package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.UserRequest;
import com.project.dto.UserResponse;
import com.project.response.PageResponse;
import com.project.service.UserService;
import com.project.util.CommonUtils;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	Create a handler to create new user:
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
		Boolean userSaved = userService.createUser(userRequest);
		
		if(userSaved) 
			return CommonUtils.createBuildResponseMessage("User Created Successfully!", HttpStatus.CREATED);
		
		return CommonUtils.createErrorResponseMessage("Failed to create user!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
//	Create a handler to get all the user:
	@GetMapping("/")
	public ResponseEntity<?> getAllUser(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "desc") String sortOrder, @RequestParam(defaultValue = "id") String sortByFeild) {
		PageResponse<?> pageResponse = userService.getAllUser(pageNo, pageSize, sortOrder, sortByFeild);
		
		return CommonUtils.createBuildResponse(pageResponse, HttpStatus.OK);
	}
}
