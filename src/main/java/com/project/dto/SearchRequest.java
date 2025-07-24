package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchRequest {

	private String keyword;
	
	private Integer experience;
	
	private String location;
	
}
