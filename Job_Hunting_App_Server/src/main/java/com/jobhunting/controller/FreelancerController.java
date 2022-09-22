package com.jobhunting.controller;

import java.util.List; 
import java.util.Optional;

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
import com.jobhunting.model.Freelancer;
import com.jobhunting.service.FreelancerService;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {
	
	@Autowired
	private FreelancerService freelancerService;
	
	//Post Mapping to add freelancer
	@PostMapping("/addFreelancer")
	public ResponseEntity<Freelancer> addNewFreelancer(@RequestBody @Valid Freelancer freelancer)
	{
	  return new ResponseEntity<Freelancer>(freelancerService.addFreelancer(freelancer), HttpStatus.CREATED);	
	}
	
	
	//Get Mapping to fetch all freelancer
	@GetMapping("/getAllFreelancerDetails")
	public ResponseEntity<List<Freelancer>> getFreelancerDetails()
	{
		return new ResponseEntity<List<Freelancer>>(freelancerService.getAllFreelancerDetails(), HttpStatus.OK);
	}
	
	
	//Get Mapping to fetch a freelancer based on freelancer Id
	@GetMapping("/getDetailsById/{freelancerId}")
	public ResponseEntity<Optional<Freelancer>> getfreelancerById(@PathVariable(value="freelancerId") int freelancerId)
	{
		return new ResponseEntity<Optional<Freelancer>>(freelancerService.getFreelancerDetailsById(freelancerId),HttpStatus.OK );
	}
	
	
	//Put Mapping to update freelancer details based on freelancer Id
	@PutMapping("/updateFreelancerDetails/{freelancerId}")
	public String updateFreelancer(@PathVariable(value="freelancerId") int freelancerId, @RequestBody @Valid Freelancer freelancer)
	{
		 return freelancerService.updateFreelancerDetails(freelancer, freelancerId);
	}
	
	
	//Delete Mapping to delete freelancer details based on freelancer Id
	@DeleteMapping("/deleteById/{freelancerId}")
	public String deleteFreelancerById(@PathVariable(value="freelancerId") int freelancerId, @RequestBody Freelancer freelancer)
	{   
		return freelancerService.deleteFreelancerById(freelancer, freelancerId);	 
	}
	
}


