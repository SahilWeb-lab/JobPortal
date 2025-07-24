package com.project.service;

import com.project.dto.JobRequest;
import com.project.dto.JobResponse;
import com.project.dto.SearchRequest;
import com.project.response.PageResponse;

public interface JobService {

//	Create a method to create job:
	public JobResponse createJob(JobRequest jobRequest);
	
//	Create a method to get all jobs: by employers
	public PageResponse<?> getJobs(Integer pageNo, Integer pageSize,String sortOrder, String sortByFeild);
	
//	Create a method to get job by id:
	public JobResponse getJobById(Long jobId);
	
//	Create a method to soft delete job or disable:
	public Boolean softDeleteJob(Long jobId);
	
//	Create a method to get all active jobs:
	public PageResponse<?> allActiveJobs(Integer pageNo, Integer pageSize,String sortOrder, String sortByFeild);
	
//	Create a method to permanent delete job:
	public Boolean deleteJob(Long jobId);
	
//	Create a method to update job:
	public Boolean updateJob(JobRequest jobRequest);
	
}
