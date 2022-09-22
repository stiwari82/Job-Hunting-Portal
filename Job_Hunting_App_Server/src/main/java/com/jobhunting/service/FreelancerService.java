package com.jobhunting.service;

import java.util.List;    
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobhunting.exception.DuplicateFreelancerFoundException;
import com.jobhunting.exception.FreelancerNotFoundException;
import com.jobhunting.model.Application;
import com.jobhunting.model.Freelancer;
import com.jobhunting.repository.ApplicationRepo;
import com.jobhunting.repository.FreelancerRepo;
@Service
public class FreelancerService {
	
	@Autowired
	private FreelancerRepo freelancerRepo;
	
	@Autowired
	private ApplicationRepo applicationRepo;
	
	//business logic to add details of new freelancer
	public Freelancer addFreelancer(Freelancer freelancer){ 
		
		Optional<Freelancer> newFreelancer = freelancerRepo.findById(freelancer.getFreelancerid());
		
		if(newFreelancer.isPresent()){
			throw new DuplicateFreelancerFoundException("Duplicate freelancer-id or details found");
		}
		else{
			return freelancerRepo.save(freelancer);
		}	
		
	}
	
    
	//business logic to get all freelancer details
	public List<Freelancer> getAllFreelancerDetails() {
		return freelancerRepo.findAll();
	}

	
	//business logic to get a particular freelancer based on freelancer Id
	public Optional<Freelancer> getFreelancerDetailsById(int id) {
		Optional<Freelancer> getdetails = freelancerRepo.findById(id);
		if(getdetails.isPresent())
		{
			return getdetails;
		}
		else
		{
			throw new FreelancerNotFoundException("Freelancer doesn't exist");
		}
	}
	
	
	//business logic to update details of a freelancer based on freelancer Id
	public String updateFreelancerDetails(Freelancer freelancer, int freelancerId){   

		Freelancer updatedetails = freelancerRepo.findById(freelancerId).orElseThrow(() -> new FreelancerNotFoundException("Freelancer doesn't exist"));
		
		updatedetails.setContactNo(freelancer.getContactNo());
		updatedetails.setExperience(freelancer.getExperience());
		updatedetails.setFreelancerName(freelancer.getFreelancerName());
		updatedetails.setRating(freelancer.getRating());
		
		freelancerRepo.save(updatedetails);
		return "Freelancer Details Updated Successfully";
	}
	
	
	//business logic to delete freelancer details based on freelancer Id
	public String deleteFreelancerById(Freelancer freelancer, int freelancerId){

		Optional<Freelancer> freelancerDetail = freelancerRepo.findById(freelancerId);
		Optional<List<Application>> freelancerApplications = applicationRepo.findByFreelancerid(freelancerId);

		if(freelancerDetail.isPresent() && freelancerApplications.isPresent() ) {

			 applicationRepo.deleteByFreelancer(freelancerId);
		     freelancerRepo.deleteById(freelancerId);

		     return "deleted successfully";
		}
		else if(freelancerDetail.isPresent()){
			freelancerRepo.deleteById(freelancerId);

			return "deleted successfully";
		}
		else{

			throw new FreelancerNotFoundException("Freelancer doesn't exist");
		}
	}
	     
 }
