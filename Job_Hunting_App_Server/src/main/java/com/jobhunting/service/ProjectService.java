package com.jobhunting.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobhunting.exception.ProjectAlreadyExistException;
import com.jobhunting.exception.ProjectNotFoundException;
import com.jobhunting.exception.RecruiterNotFoundException;
import com.jobhunting.model.Application;
import com.jobhunting.model.Project;
import com.jobhunting.repository.ApplicationRepo;
import com.jobhunting.repository.ProjectRepo;
import com.jobhunting.repository.RecruiterRepo;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired 
	RecruiterRepo recruiterRepo;
	
	@Autowired 
	ApplicationRepo applicationRepo;
	
	//business logic to add a project
	public Project addProject(Project project) {
		Optional<Project> proj = projectRepo.findById(project.getProjId());
		
		if(proj.isPresent()) {
			throw new ProjectAlreadyExistException("Project already exist with above project Id");
		}
		else {
			if(recruiterRepo.findById(project.getRecruiter().getRecId()).isPresent()) {
				return projectRepo.save(project);
			}
			else {
				throw new RecruiterNotFoundException("Recruiter-Id Not Found");
			}
		}

	}
	
	
	//business logic to fetch all project details
	public List<Project> getProject()
	{
		return projectRepo.findAll();
	}
	
	
	//business logic to fetch a project details based on project Id
	public Optional<Project> getProjectById(int id)
	{
		Optional<Project> project = projectRepo.findById(id);
		
		if(project.isPresent())
		{
			return project;
		}
		else
		{
			throw new ProjectNotFoundException("Project Not Found");
		}
	}
	
	
	//business logic to delete a project based on project Id from project or from application as well
	public void deleteProjectById(int id)
	{
		Optional<Project> project = projectRepo.findById(id);
		System.out.println(project);
		List<Application> application = applicationRepo.findApplicationByPid(id);
		System.out.println(application);
	
		if(project.isPresent() && application.isEmpty()) 
		{
			projectRepo.deleteById(id);
		}
		
		else if(project.isPresent() && !application.isEmpty()) {
			
			applicationRepo.deleteByApplication(id);
			projectRepo.deleteById(id);
		}
		else 
		{
			throw new ProjectNotFoundException("Project Not Found");
			
		}
		
	}
	
	//business logic to update a project details based on project Id
	public Project updateProjectById(int id, Project project)
	{
		Project proj = projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));
		
		proj.setDuration(project.getDuration());
		proj.setUploadDate(project.getUploadDate());
		proj.setDescription(project.getDescription());
		proj.setBudget(project.getBudget());
		
		Project updateProject = projectRepo.save(proj);
		return updateProject;
	}
	
	//business logic to fetch all the freelancer applications based on project Id
	public List<Application> getApplicationDetailsById(int projId) {

		List<Application> application = projectRepo.findApplicationByProjId(projId);
			
		if(!application.isEmpty()) {
			return application;
		}

		throw new ProjectNotFoundException("Project doesn't exist");
	}
	
	//business logic to view all available projects
	public ArrayList<Project> viewAvailableApplication() {

		 ArrayList<Project> project=new ArrayList<>();
		 ArrayList<Project> availableProject=new ArrayList<>();

		 project.addAll(projectRepo.findAll());

		 for(int i=0;i<project.size();i++) {

		  ArrayList<String> projectStatus=new ArrayList<>();
		  projectStatus.addAll(applicationRepo.findStatus(project.get(i).getProjId()));

		  System.out.println(projectStatus);
		  projectStatus.replaceAll(String::toLowerCase);

		  if(!projectStatus.contains("approved")) {
			  availableProject.add(project.get(i));
		  }
	  }

		 if(availableProject.isEmpty()) {
			 throw new ProjectNotFoundException("No project is available right now ");
		 }
		 	return availableProject;
	 }
}
