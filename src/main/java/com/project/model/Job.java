package com.project.model;

import com.project.enums.Department;
import com.project.enums.IndustryType;
import com.project.enums.WorkMode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
