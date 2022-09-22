package com.jobhunting.controller;

import java.util.ArrayList;
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

import com.jobhunting.model.Project;
import com.jobhunting.repository.ProjectRepo;
import com.jobhunting.repository.RecruiterRepo;
import com.jobhunting.service.ProjectService;


@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired 
	ProjectRepo projectRepo;
	
	@Autowired 
	RecruiterRepo recruiterRepo;
	
	//Get Mapping to fetch all projects
	@GetMapping("/viewAllProjects")
	public ResponseEntity<List<Project>> viewProject(){
		
		return new ResponseEntity<List<Project>>(projectService.getProject(), HttpStatus.OK);
	}
	
	//Post Mapping to upload a project
	@PostMapping("/uploadProject")
	public ResponseEntity<Object> addProject(@RequestBody @Valid Project project) {
		
		projectService.addProject(project);
		
		return new ResponseEntity<>("Project is uploded successfully", HttpStatus.CREATED);
	}
	
	
	//Get Mapping to fetch a project based on project Id
	@GetMapping("/viewProject/{projId}")
	public ResponseEntity<Optional<Project>> viewProjectById(@PathVariable int projId){
		
		return new ResponseEntity<Optional<Project>>(projectService.getProjectById(projId), HttpStatus.OK);
	}
	
	
	//Put Mapping to update project details based on project Id
	@PutMapping("updateProject/{projId}")
	public ResponseEntity<Object> updateProject(@PathVariable int projId, @RequestBody @Valid Project project){
		
		projectService.updateProjectById(projId,project);
		
		return new ResponseEntity<Object>("Project is updated successfully",HttpStatus.OK);
	}
	
	
	//Delete Mapping to delete a project based on project Id
	@DeleteMapping("deleteProject/{projId}")
	public String deleteProjectById(@PathVariable int projId){
		
		projectService.deleteProjectById(projId);
		
		return "Project deleted successfully";
	}
	
	//get mapping to fetch application based on project Id
	@GetMapping("getApplications/{projId}")
	public ResponseEntity<Object> viewApplicationByProjectId(@PathVariable int projId){
		
		return new ResponseEntity<Object>(projectService.getApplicationDetailsById(projId), HttpStatus.OK);
	}
	
	
	 //get all available project
	 @GetMapping("/getAvailableApplications")
	 public ResponseEntity<ArrayList<Project>> viewAvailableApplication(){

		 return new ResponseEntity<ArrayList<Project>>(projectService.viewAvailableApplication(), HttpStatus.OK);
	 }
		
}
