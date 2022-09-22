package com.jobhunting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobhunting.exception.AlreadyAppliedException;
import com.jobhunting.exception.ApplicationClosedException;
import com.jobhunting.exception.ApplicationDeleteException;
import com.jobhunting.exception.ApplicationNotFoundException;
import com.jobhunting.exception.DuplicateApplicationException;
import com.jobhunting.exception.FreelancerNotFoundException;
import com.jobhunting.exception.InvalidStatusUpdateException;
import com.jobhunting.exception.NotYetAppliedException;
import com.jobhunting.exception.ProjectNotFoundException;
import com.jobhunting.exception.RecruiterNotFoundException;
import com.jobhunting.model.Application;
import com.jobhunting.model.Project;
import com.jobhunting.repository.ApplicationRepo;
import com.jobhunting.repository.FreelancerRepo;
import com.jobhunting.repository.ProjectRepo;

@Service
public class ApplicationService {

	@Autowired
	FreelancerRepo freelancerRepo;

	@Autowired
	ProjectRepo projectRepo;

	@Autowired
	ApplicationRepo applicationRepo;
    
	//business logic for approval/rejection/pending status of an application updating
		public Application approveProject(int appId, String status, int recId) {
			
			if(applicationRepo.findById(appId).isEmpty()){
					throw new ApplicationNotFoundException("Application doesn't exist");
			}
			else
			{
				if(projectRepo.findById((applicationRepo.findById(appId)).get().getProject().getProjId()).get().getRecruiter().getRecId()!=recId){
					throw new RecruiterNotFoundException("Incorrect Recruiter I'd");
				}
				else
				{
					if(!(status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("approved") || status.equalsIgnoreCase("rejected"))) {
							throw new InvalidStatusUpdateException("Status must be pending,approved or rejected");
						}
					else 
						{
							Application ap=applicationRepo.findById(appId).orElseThrow(()-> new ApplicationNotFoundException("Application doesn't exist"));	
							ap.setStatus(status);
							
							applicationRepo.save(ap);
							return ap;
					    }
				   }
			  }
		 }
		
	//business logic to apply for a project
	public Application applyForProject(Application application){
		
		Optional<Application> applicationId = applicationRepo.findById(application.getApplicationId());
		Optional<Object> freelancer = freelancerRepo.findByFreelancerid(application.getFreelancer().getFreelancerid());
		List<Integer> projectId = applicationRepo.findProjectIdByFreelancer(application.getFreelancer().getFreelancerid());
		Optional<Project> project = projectRepo.findByProjId(application.getProject().getProjId());


		if(applicationId.isPresent()){
			throw new DuplicateApplicationException("Application i'd already exists");
		}
		
		if(!freelancer.isPresent()){
			throw new FreelancerNotFoundException("Freelancer doesn't exist");
		}
		
		if(project.isEmpty()){
			throw new ProjectNotFoundException("Project Not Found");
		}
		
		if(projectId.contains(application.getProject().getProjId())){
			throw new AlreadyAppliedException("You have already applied for this project");
		}

		else {
				ArrayList<String> status=new ArrayList<>();
				status.addAll(applicationRepo.findStatus(application.getProject().getProjId()));

			    for(int i=0;i<status.size();i++) {
			       if(status.get(i).equalsIgnoreCase("approved")) {
			    	   throw new ApplicationClosedException("Application closed for this project");
			       }
			  }
			  return applicationRepo.save(application);
		}
	}
	
	
	//business logic for freelancer to view all applied project based on freelancer Id
	public List<Application> viewAllAppliedProject(int fid){

		if(freelancerRepo.findById(fid).isPresent()){

			if(!applicationRepo.findApplication(fid).isEmpty()){
				return applicationRepo.findApplication(fid);
			}

			else{
				throw new NotYetAppliedException("You haven't applied for any project");
			}
		}

		throw new FreelancerNotFoundException("Freelancer doesn't exist");
	}
	
	
	//business logic for freelancer to view a applied application based on freelancer Id & Project Id
	public Application viewSpecificAppliedProject(int fid,int pid){

		if(freelancerRepo.findById(fid).isPresent()){

			ArrayList<Application> ap=applicationRepo.findApplicationByPid(pid);

			for(int i=0;i<ap.size();i++){
				if(ap.get(i).getFreelancer().getFreelancerid()==fid){
					return ap.get(i);
				}
			}
		}

		else{
			throw new FreelancerNotFoundException("Freelancer doesn't exist");
		}

		throw new ApplicationNotFoundException("Application doesn't exist");
	}
	
	
	//business logic to delete an application which is not approved 
	public void deleteApplicationById(int applicationId) {

	 if(applicationRepo.findById(applicationId).isEmpty()) {
	   throw new ApplicationNotFoundException("Application doesn't exist");
	 }
	 else {
		 if(applicationRepo.findById(applicationId).get().getStatus().equalsIgnoreCase("approved")) {
			    throw new ApplicationDeleteException("Couldn't delete because your application is already approved");
		 	}

		    applicationRepo.deleteById(applicationId);
	 	}
	 }
	
}