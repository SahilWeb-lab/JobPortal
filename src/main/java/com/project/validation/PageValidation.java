package com.project.validation;

import org.springframework.stereotype.Component;

@Component
public class PageValidation {

	public void validate(Integer pageNo, Integer pageSize) {
		
		if(pageNo <= 0) 
			throw new IllegalArgumentException("Page Number must not be less than one.");
		
		if(pageSize <= 0)
			throw new IllegalArgumentException("Page size must not be less than one.");

	}
	
}
