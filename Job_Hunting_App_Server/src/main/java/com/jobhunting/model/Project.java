package com.jobhunting.model;


import java.sql.Date; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int projId;
	
	@NotBlank(message = "Duration can not be Blank*")
	@Size(max = 20, message = "Duration length should not be more than 20*")
	private String duration;
	
	@ManyToOne	//many projects can exist for one recruiter
	@JoinColumn(name = "recId")
	private Recruiter recruiter;
	
	@NotBlank(message = "Description can not be Blank*")
	@Size(max = 50, message = "Description length should not be more than 50*")
    private String description;
	
	@Min(value = 10000, message = "Budget must be more than Rs.10000*")
	private double budget;
	
	private Date uploadDate;
	
}
