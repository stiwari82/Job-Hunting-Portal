package com.jobhunting.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobhunting.model.Freelancer;

@Repository
public interface FreelancerRepo extends JpaRepository<Freelancer, Integer> {
  
	  //Query to fetch freelancer object based on freelancer Id 
	  @Query(" from Freelancer where freelancerid=?1")
	  Optional<Object> findByFreelancerid(int id);

   }
