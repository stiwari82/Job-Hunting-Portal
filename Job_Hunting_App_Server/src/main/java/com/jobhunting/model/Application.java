package com.jobhunting.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Application {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int applicationId;
	
	@NotBlank(message = "Status can not be Blank*")
	@Size(max = 10, message = "Status length should not be more than 10*")
	private String status;
	
	private Date applyDate;
	
    @ManyToOne // many applications can exist for one project
    @JoinColumn(name = "projId")
    private Project project;

    @ManyToOne  // many applications can exist for one freelancer
    @JoinColumn(name = "freelancerid")
    private Freelancer freelancer;
 
}
