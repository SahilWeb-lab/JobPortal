package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

//	Create a method to disable or delete the job:
	@Transactional
	@Modifying
	@Query("UPDATE Job j SET j.enabled = false where id=:jobId")
	public Integer disableJob(@Param(value = "jobId") Long jobId);
	
//	Create a method to get all enabled jobs only:
	@Query("SELECT j FROM Job j WHERE j.enabled = true")
    Page<Job> findAllEnabledJobs(Pageable pageable);
	
//	Create a method to search jobs:
}
