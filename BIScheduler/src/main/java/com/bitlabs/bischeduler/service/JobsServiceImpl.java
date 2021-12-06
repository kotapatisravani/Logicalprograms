package com.bitlabs.bischeduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.bischeduler.entity.Jobs;
import com.bitlabs.bischeduler.repository.JobsRepositoryInterface;


@Service
public class JobsServiceImpl implements JobsServiceInterface {

	
	@Autowired
	private JobsRepositoryInterface jri;

	@Override
	public boolean saveJobData(Jobs job) {
		
		Jobs j= job;
		boolean b=false;
		if(j!=null) {
			jri.save(job);
			 b=true;
		}
			return b;
	}

	@Override
	public List<Jobs> getJobDetails() {
		
		return jri.findAll();
	}

	@Override
	public boolean deleteJobDetails(Jobs job) {
		boolean b=false;
        List<Jobs> j=jri.findAll();
        for(Jobs d:j)
    	  if(d.getId()==job.getId()) {
    		  
		     jri.deleteById(job.getId());
		     b=true;
    	 }
        return b;
	}

	

	@Override
	public Jobs getJobById(Jobs job) {
		//boolean b=false;
		List<Jobs> all=jri.findAll();
		for(Jobs a:all)
			if(a.getId()==job.getId()) {
				
			return jri.findById(job.getId()).get();
			}
		return job;
			
		
	}
	@Override
	public boolean updateJobDetail(int id, Jobs jobdetail) {
		List<Jobs> job=jri.findAll();   
		boolean b=false;
		for(Jobs j:job)
		 if(j.getId()==jobdetail.getId()) {
             Jobs jd=jri.findById(id).get();
		     jobdetail.setCreated_at(jd.getCreated_at());

			 jri.save(jobdetail);
			 b=true;
		 }
			return b;
		
	}

	@Override
	public List<Jobs> getJobByName(String jname) {
		
		return jri.getJobsByJobname(jname);
	}
}
