package com.jobhunting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobhunting.model.Recruiter;

@Repository
public interface RecruiterRepo extends JpaRepository<Recruiter, Integer>{

	//method to find recruiter based on recruiter Id OR, recruiter name
	Optional<Recruiter> findByRecIdOrRecName(int recId, String recName);
	
	
	 //Query to fetch Recruiter Object based on recruiter Id
	 @Query(" from Recruiter where recId=?1")
	 Optional<Object> findByrecid(int id);

  }
