package com.jobhunting.model;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Recruiter {
	
    @Id
	private int recId;
    
    @NotBlank(message = "Recruiter Name can not be Blank*")
    @Size(max = 20, message = "Recuriter name length should not be more than 20*")
	private String recName;
    
    @NotBlank(message = "Recruiter contact number can not be Blank*")
    @Pattern(regexp = "(^[6-9][0-9]{9}$)",message = "Contact number should be 10 digits and starting with 6/7/8/9*")
	private String contactNo;
	
}
