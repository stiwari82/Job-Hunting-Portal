package com.jobhunting.repository;

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
public interface ProjectRepo extends JpaRepository<Project,Integer> {
     	
	   //Query to delete project based on recruiter Id
	   @Modifying
	   @Query("delete from Project where rec_id=?1")
	   void deleteByProject(int id);
	   
	   
	   //Query to fetch all projects based on recruiter Id
	   @Query("from Project where rec_id=?1")
	   List<Project> findByRecid(int id);
	   
       
	   //Query to fetch a project based on project Id
	   @Query("from Project where proj_Id=?1")
	   Optional<Project> findByProjId(int id);
	   
	   
	   //Query to fetch all application based on project Id
	   @Query("from Application where proj_Id=?1")
	   List<Application> findApplicationByProjId(int id);
  }
