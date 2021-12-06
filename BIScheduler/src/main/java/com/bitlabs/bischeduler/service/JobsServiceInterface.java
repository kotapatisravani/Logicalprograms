package com.bitlabs.bischeduler.service;

import java.util.List;

import javax.validation.Valid;

import com.bitlabs.bischeduler.entity.Jobs;

public interface JobsServiceInterface {

	boolean saveJobData(@Valid Jobs job);

	List<Jobs> getJobDetails();

	Jobs getJobById(Jobs job);

	boolean deleteJobDetails(Jobs job);

	boolean updateJobDetail(int id, Jobs jobdetail);

	List<Jobs> getJobByName(String jname);

}
