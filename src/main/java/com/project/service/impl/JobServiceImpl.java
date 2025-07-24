package com.project.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.dto.JobRequest;
import com.project.dto.JobResponse;
import com.project.dto.SearchRequest;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Job;
import com.project.repository.JobRepository;
import com.project.response.PageResponse;
import com.project.service.JobService;
import com.project.validation.PageValidation;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PageValidation pageValidation;
	
	@Override
	public JobResponse createJob(JobRequest jobRequest) {
		Job job = modelMapper.map(jobRequest, Job.class);
		Job saveJob = jobRepository.save(job);
		
		return modelMapper.map(saveJob, JobResponse.class);
	}

	@Override
	public PageResponse<?> getJobs(Integer pageNo, Integer pageSize,String sortOrder, String sortByFeild) {
		
//		Validate Page Request:
		pageValidation.validate(pageNo, pageSize);
		
//		Sorting Condition:
		Direction direction = (sortOrder.toUpperCase().equals("DESC")) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(direction, sortByFeild));
		Page<Job> jobs = jobRepository.findAll(pageable);
		
		PageResponse pageResponse = modelMapper.map(jobs, PageResponse.class);
		pageResponse.setPageNumber(pageResponse.getPageNumber() + 1);
		return pageResponse;
	}

	@Override
	public JobResponse getJobById(Long jobId) {
		Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found with id ["+ jobId +"]"));
		
		return modelMapper.map(job, JobResponse.class);
	}

	@Override
	public Boolean softDeleteJob(Long jobId) {
		jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found with id ["+ jobId +"]"));
		Integer disabled = jobRepository.disableJob(jobId);
		return (disabled > 0) ? true : false;
	}

	@Override
	public PageResponse<?> allActiveJobs(Integer pageNo, Integer pageSize,String sortOrder, String sortByFeild) {
//		Validate Page Request:
		pageValidation.validate(pageNo, pageSize);
		
//		Sorting Condition:
		Direction direction = (sortOrder.toUpperCase().equals("DESC")) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(direction, sortByFeild));
		Page<Job> jobs = jobRepository.findAllEnabledJobs(pageable);
		
		PageResponse pageResponse = modelMapper.map(jobs, PageResponse.class);
		pageResponse.setPageNumber(pageResponse.getPageNumber() + 1);
		return pageResponse;
	}

	@Override
	public Boolean deleteJob(Long jobId) {
		jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found with id ["+ jobId +"]"));
		
		jobRepository.deleteById(jobId);
		return true;
	}

	@Override
	public Boolean updateJob(JobRequest jobRequest) {
		Job job = modelMapper.map(jobRequest, Job.class);
		Job updatedJob = jobRepository.save(job);
		
		if(ObjectUtils.isEmpty(updatedJob))
			return false;
		
		return true;
	}
	
}
