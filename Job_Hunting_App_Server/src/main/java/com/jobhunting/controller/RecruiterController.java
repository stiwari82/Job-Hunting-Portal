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


import com.jobhunting.model.Recruiter;
import com.jobhunting.service.RecruiterService;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
	
	@Autowired
	private RecruiterService recruiterService;
	
	//Get Mapping to fetch all recruiter details
	@GetMapping("/getAllRecruiters")
	public List<Recruiter> getAllRecruiter(){
		
		return recruiterService.getRecruiter();
	}

	
	//Get Mapping to fetch a recruiter details by recruiter Id
	@GetMapping("/getRecruiterById/{recId}")
	public ResponseEntity<Optional<Recruiter>> getRecruiterById(@PathVariable("recId") int recId, Recruiter recruiter){
		
		return new ResponseEntity<Optional<Recruiter>>(recruiterService.getRecruiterById(recId), HttpStatus.OK);
	}
	
	
	//Post Mapping to add new recruiter
	@PostMapping("/addRecruiter")
	public ResponseEntity<Recruiter> addNewRecruiter(@RequestBody @Valid Recruiter recruiter){
		
		return new ResponseEntity<Recruiter>(recruiterService.addRecruiter(recruiter), HttpStatus.CREATED);	
	}
	
	
	//Put Mapping to update recruiter details based on recruiter Id 
	@PutMapping("/updateRecruiter/{recId}")
	public String updateRecruiterDetails(@RequestBody @Valid Recruiter recruiter, @PathVariable("recId") int recId) {
		
		return recruiterService.updateRecruiterDetails(recruiter, recId);
	}
	
	
	//Delete Mapping to delete a recruiter details based on recruiter Id
	@DeleteMapping("/deleteRecruiter/{recId}")
	 public String deleteRecruiterById(@PathVariable("recId") int recId, @RequestBody Recruiter recruiter) {
		
		return recruiterService.deleteRecruiterById(recruiter, recId);
	}
	
}

