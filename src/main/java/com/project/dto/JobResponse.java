package com.project.dto;

import com.project.enums.Department;
import com.project.enums.IndustryType;
import com.project.enums.WorkMode;
import com.project.model.ContractType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobResponse {

	private Long id;
	
	private String title;
	
	private String description;
	
	private String company;
	
	private String opening;
	
	private String skills;
	
	private String salary;
	
	private String location;
	
	private WorkMode workMode;
	
	private ContractType contractType;
	
	private Integer experience;
	
	private String noticePeriod;
	
	private String jobRole;
	
	private IndustryType industryType;
	
	private Department department;
	
	private String education;
	
	private Boolean enabled = true;
	
	private Boolean scheduled = false;
	
}
