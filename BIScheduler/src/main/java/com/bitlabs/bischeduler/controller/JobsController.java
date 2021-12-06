package com.bitlabs.bischeduler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.bischeduler.entity.Jobs;
import com.bitlabs.bischeduler.response.ResponseHandler;
import com.bitlabs.bischeduler.service.JobsServiceInterface;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class JobsController {

	@Autowired
	private JobsServiceInterface jsi;
	
	@PostMapping("/savejobs")
	public ResponseEntity<Object> saveJob(@Valid @RequestBody Jobs job)
	{
		 boolean b=jsi .saveJobData(job);
		 if(b==true)
		      return ResponseHandler.generateResponse(" JOb saved Successfully ", HttpStatus.OK,b);
		 else 
			 return ResponseHandler.generateResponse(" Job not saved successfully  ", HttpStatus.BAD_REQUEST,b); 
	}
	
	@GetMapping("/getalljob")
	public ResponseEntity<Object> getAllJobDetails(){
		
		
		List<Jobs> a=jsi.getJobDetails();
		String b=null;
		if(a!=null) {
			return ResponseHandler.generateResponse(" Whole data ", HttpStatus.OK,a);
		}
		else {
			return ResponseHandler.generateResponse(" No data ", HttpStatus.BAD_REQUEST,b);
		}
	}
	
	@GetMapping("/getjobs")
	public ResponseEntity<Object> getJobById(@RequestBody Jobs job) {
	        Jobs b=jsi.getJobById(job);
	       if(b!=job)  
	         return ResponseHandler.generateResponse(" existed id ", HttpStatus.OK,b);
	       else 
	    	   return ResponseHandler.generateResponse("Your id is not existed ", HttpStatus.BAD_REQUEST,false);   
	}
	
	@DeleteMapping("/deletejob")
	 public ResponseEntity<Object> deleteJobDetails(@RequestBody Jobs job) {
			
			boolean b=jsi.deleteJobDetails(job);
		if(b==true)	
			return ResponseHandler.generateResponse(" Deleted Successfully ", HttpStatus.OK,b);
		
		else 
			return ResponseHandler.generateResponse(" entered Id not existed ", HttpStatus.BAD_REQUEST,b);
		}
	
	@PutMapping("/updatejob")
	public ResponseEntity<Object> updateJobDetails(@RequestBody Jobs jobdetail) {
		
		 boolean b=jsi.updateJobDetail(jobdetail.getId(),jobdetail);
		 if(b==true)
			 return ResponseHandler.generateResponse(" Updated Successfully ", HttpStatus.OK,b);
		 else 
			 return ResponseHandler.generateResponse(" U cannot update ", HttpStatus.OK,b);
	}
	
	@GetMapping("/getJobDeatailByName")
	public List<Jobs> getJobByName(@RequestBody Jobs job){
		return jsi.getJobByName(job.getJobname());
	}
}
