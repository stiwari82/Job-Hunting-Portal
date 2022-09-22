package com.jobhunting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobhunting.model.Application;
import com.jobhunting.service.ApplicationService;

@RestController
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	//apply for a project
	@PostMapping("/apply")
	public ResponseEntity<Application> applyForProject(@RequestBody @Valid Application application)
	{
		return new ResponseEntity<Application>(applicationService.applyForProject(application), HttpStatus.CREATED);
	}
	
	
	//update the status of the application i.e. (accepted/rejected/pending)
	@PutMapping("/approve/{applicationId}/{status}/{recruiterId}")
	public ResponseEntity<Object> approveProject(@PathVariable("applicationId") Integer appid,@PathVariable("status") String status,@PathVariable("recruiterId") Integer recId)
	{	
		applicationService.approveProject(appid,status,recId);
		
		return new ResponseEntity<>("Status uploaded successfully", HttpStatus.OK);
	}
	
	
	//Fetch all applied applications for a particular freelancer
	@GetMapping("/viewAllAppliedProject/{freelancerId}")
	 public ResponseEntity<Object> viewAppliedProject(@PathVariable("freelancerId") Integer fId){

		return new ResponseEntity<>(applicationService. viewAllAppliedProject(fId), HttpStatus.OK);
	 }
	
	
	//Fetch a applied aplication for a project of a particular freelancer
	 @GetMapping("/viewSpecificProject/{freelancerId}/{projectId}")
	 public ResponseEntity<Object> viewSpecificAppliedProject(@PathVariable("freelancerId") Integer fId, @PathVariable("projectId") Integer pId){

		 return new ResponseEntity<>(applicationService.viewSpecificAppliedProject(fId,pId), HttpStatus.OK);

	 }
	 
	 //Delete application based on application Id
	 @DeleteMapping("deleteApplication/{applicationId}")
	 public String deleteApplicationById(@PathVariable int applicationId) {
		 
	   applicationService.deleteApplicationById(applicationId);
	   return "Application deleted successfully";
	}
	
}
