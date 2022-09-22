package com.jobhunting.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jobhunting.model.Application;
import com.jobhunting.model.Project;


@Repository
@Transactional
public interface ApplicationRepo extends JpaRepository<Application,Integer> {
	  
	   //Freelancer All Queries ------->
	
	   //Query to delete freelancer all applications based on freelancer Id 
	   @Modifying
	   @Query("delete from Application where freelancerid=?1")
	   void deleteByFreelancer(int id);
	
	   //Query to fetch freelancer all applications based on freelancer Id 
	   @Query("from Application where freelancerid=?1")
	   Optional<List<Application>> findByFreelancerid(int freelancerId);

	   //Query to fetch project based on project Id
	   @Query("from Project where proj_id=?1")
	   Optional<Project> findByProjectid(int id);
	   
	   //Query to delete application based on project Id
 	   @Modifying
 	   @Query("delete from Application where proj_id=?1")
 	   void deleteByApplication(int id);
 	   
 	   //Query to fetch all application based on project Id
 	   @Query("from Application where proj_id=?1")
 	   ArrayList<Application> findByProjid(int id); 
 	  
 	   
 	   //Application All Queries ---------->
 	   
 	    //ManytoOne Mapping
 	   //Query to fetch all project IDs for which a freelancer applied 
 	   @Query(value = "select proj_id from Application where freelancerid =?1", nativeQuery = true)
 	   List<Integer> findProjectIdByFreelancer(int id);
 	  
 	   //Query to fetch all application based on project Id
 	   @Query("from Application where proj_id=?1")
 	   ArrayList<Application> findApplicationByPid(int pid);
 	 
 	 
	    //Query to fetch all application based on freelancer Id
	 	@Query("from Application where freelancerid=?1")
	 	List<Application> findApplication(int id);
	 	
	 	
	 	//Query to fetch status of applications of a particular project
	 	@Query("select status from Application where proj_id=?1")
	 	ArrayList<String> findStatus(int pid);
 	 
   }
