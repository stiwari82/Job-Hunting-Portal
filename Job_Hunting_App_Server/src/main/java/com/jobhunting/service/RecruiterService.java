package com.jobhunting.service;

import java.util.ArrayList;
import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jobhunting.exception.DuplicateRecruiterFoundException;
import com.jobhunting.exception.RecruiterNotFoundException;
import com.jobhunting.model.Application;
import com.jobhunting.model.Project;
import com.jobhunting.model.Recruiter;
import com.jobhunting.repository.ApplicationRepo;
import com.jobhunting.repository.ProjectRepo;
import com.jobhunting.repository.RecruiterRepo;


@Service
public class RecruiterService {
	
	@Autowired
	private RecruiterRepo recruiterRepo;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private ApplicationRepo applicationRepo;
	
	//saving a specific record by using this method save()
	//business logic to add a new recruiter
	public Recruiter addRecruiter(Recruiter recruiter) {
		Optional<Recruiter> rec = recruiterRepo.findByRecIdOrRecName(recruiter.getRecId(), recruiter.getRecName());
		
		if(rec.isPresent()) {
			throw new DuplicateRecruiterFoundException("Duplicate recruiter Id or name found");
		}
		else {
			return recruiterRepo.save(recruiter);
		}
		
	}
	
	
	//getting all recruiter record using the method findAll()
	//business logic to fetch all recruiter details
	public List<Recruiter> getRecruiter(){
		return recruiterRepo.findAll();
	}
	
	
	//getting a specific record by using the method findById()
	//business logic to fetch a recruiter details based on recruiter Id
	public Optional<Recruiter> getRecruiterById(int recId) {
		
		Optional<Recruiter> recruiter = recruiterRepo.findById(recId);
		
		if(recruiter.isPresent()) {
			return recruiter;
		}
		else {
			throw new RecruiterNotFoundException("Recruiter-Id Not Found");
		}
	}
	
	
	//business logic for updating record of a recruiter based on recruiter Id
	public String updateRecruiterDetails(Recruiter recruiter, int recId) {
		
		Recruiter updatedetails= recruiterRepo.findById(recId).orElseThrow(() -> new RecruiterNotFoundException("Recruiter-Id Not Found"));
		
		updatedetails.setContactNo(recruiter.getContactNo());
		updatedetails.setRecName(recruiter.getRecName());
		
		recruiterRepo.save(updatedetails);
		return "Recruiter details updated successfully";	
	}
	
	
	//deleting a specific record by using the method deleteById()
	//business logic to delete a recruiter based on recruiter Id
	public String deleteRecruiterById(Recruiter recruiter ,int recId) {    
		Optional<Recruiter> rec = recruiterRepo.findById(recId);
		
		if(rec.isEmpty()) {
			throw new RecruiterNotFoundException("Recruiter-Id Not Found");
		}
			   List<Project> project = projectRepo.findByRecid(recId);
			   ArrayList<Application> application = new ArrayList<>();
			   
			   for(int i = 0;i<project.size();i++) {
				   if(applicationRepo.findByProjid(project.get(i).getProjId()).isEmpty()) {
					   continue;
				   }
				   System.out.println(i);
				   application.addAll(applicationRepo.findByProjid(project.get(i).getProjId()));
			   }
			
			   if(rec.isPresent() && !project.isEmpty() && !application.isEmpty() ){   
				   
				   for(int i = 0;i<project.size();i++) {
					  applicationRepo.deleteByApplication(project.get(i).getProjId());
				   }
				   
				    projectRepo.deleteByProject(recId);
				    recruiterRepo.deleteById(recId);
	       
				    return "deleted successfully";
			   }
			   
			   else if(rec.isPresent() && !project.isEmpty()){
				   
					 projectRepo.deleteByProject(recId);
					 recruiterRepo.deleteById(recId);
			 
					 return "deleted successfully";
			    }
			   else {
				 recruiterRepo.deleteById(recId);
				 
				 return "deleted successfully";
			}
		}
	
    }
