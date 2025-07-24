package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.JobRequest;
import com.project.dto.JobResponse;
import com.project.dto.SearchRequest;
import com.project.response.PageResponse;
import com.project.service.JobService;
import com.project.util.CommonUtils;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

	@Autowired
	private JobService jobService;
	
//	Create a handler to create job:
	@PostMapping("/")
	public ResponseEntity<?> createJob(@RequestBody JobRequest jobRequest) {
		JobResponse jobResponse = jobService.createJob(jobRequest);
		
		if(ObjectUtils.isEmpty(jobResponse))
			return CommonUtils.createErrorResponseMessage("Failed to create job!", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return CommonUtils.createBuildResponse(jobResponse, HttpStatus.OK);
	}
	
//	Create a handler to get all the jobs:
	@GetMapping("/")
	public ResponseEntity<?> getAllJobs(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "desc") String sortOrder, @RequestParam(defaultValue = "id") String sortByFeild) {
		PageResponse<?> pageResponse = jobService.getJobs(pageNo, pageSize, sortOrder, sortByFeild);
		
		return CommonUtils.createBuildResponse(pageResponse, HttpStatus.OK);
	}
	
//	Create a handler to get job by id:
	@GetMapping("/{jobId}")
	public ResponseEntity<?> getJobById(@PathVariable Long jobId) {
		JobResponse job = jobService.getJobById(jobId);
		
		if(ObjectUtils.isEmpty(job))
			return CommonUtils.createErrorResponseMessage("Job not found with id : ["+ jobId +"]", HttpStatus.NOT_FOUND);
		
		return CommonUtils.createBuildResponse(job, HttpStatus.OK);
	}
	
//	Create a handler to disable the job:
	@PutMapping("/{jobId}")
	public ResponseEntity<?> softDeleteJob(@PathVariable Long jobId) {
		Boolean disabled = jobService.softDeleteJob(jobId);
		
		if(disabled)
			return CommonUtils.createBuildResponseMessage("Job Deleted Successfully!", HttpStatus.OK);
	
		return CommonUtils.createErrorResponseMessage("Failed to delete job!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	Create a handler to get all active jobs:
	@GetMapping("/active")
	public ResponseEntity<?> getAllActiveJobs(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "desc") String sortOrder, @RequestParam(defaultValue = "id") String sortByFeild) {
		PageResponse<?> allActiveJobs = jobService.allActiveJobs(pageNo, pageSize, sortOrder, sortByFeild);
		
		return CommonUtils.createBuildResponse(allActiveJobs, HttpStatus.OK);
	}
	
//	Create a handler to delete permanently jobs:
	@DeleteMapping("/{jobId}")
	public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
		Boolean isDeleted = jobService.deleteJob(jobId);
		
		if(isDeleted)
			return CommonUtils.createBuildResponseMessage("Job Deleted Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to delete job!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	Create a handler to update job:
	@PostMapping("/update")
	public ResponseEntity<?> updateJob(@RequestBody JobRequest jobRequest) {
		Boolean updatedJob = jobService.updateJob(jobRequest);
		
		if(updatedJob)
			return CommonUtils.createBuildResponseMessage("Job Updated Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to create job!", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
