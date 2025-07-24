package com.project.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

	private List<T> content;
	
	private Integer pageNumber;
	
	private Integer size;
	
	private Long totalElements;
	
	private Long totalPages;

	private Boolean first;
	
	private Boolean last;
	
}
