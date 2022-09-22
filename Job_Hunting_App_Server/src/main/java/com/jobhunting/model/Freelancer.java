package com.jobhunting.model;
   
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Freelancer {
	
   @Id
   private int freelancerid;
   
   @NotBlank(message = "Freelancer experience can not be Blank*")
   @Size(max = 10, message = "Freelancer experience length should not be more than 10*")
   private String experience;
   
   @NotBlank(message = "Freelancer Name can not be Blank*")
   @Size(max = 30, message = "Freelancer name length should not be more than 30*")
   private String freelancerName;
   
   @NotBlank(message = "Freelancer contact number can not be Blank*")
   @Pattern(regexp = "(^[6-9][0-9]{9}$)",message = "Mobile number should be 10 digits and starting with 6/7/8/9*")
   private String contactNo;
   
   @DecimalMin(value = "0.1", inclusive = true)
   @DecimalMax(value = "5.0", inclusive = true)
   private double rating;
   
}
