package com.jobhunting.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobhunting.exception.AlreadyAppliedException;
import com.jobhunting.exception.ApplicationClosedException;
import com.jobhunting.exception.ApplicationDeleteException;
import com.jobhunting.exception.ApplicationNotFoundException;
import com.jobhunting.exception.DuplicateApplicationException;
import com.jobhunting.exception.DuplicateFreelancerFoundException;
import com.jobhunting.exception.DuplicateRecruiterFoundException;
import com.jobhunting.exception.FreelancerNotFoundException;
import com.jobhunting.exception.InvalidStatusUpdateException;
import com.jobhunting.exception.NotYetAppliedException;
import com.jobhunting.exception.ProjectAlreadyExistException;
import com.jobhunting.exception.ProjectNotFoundException;
import com.jobhunting.exception.RecruiterNotFoundException;
import com.jobhunting.model.ErrorModel;

@RestControllerAdvice
public class GlobalExceptionController {
	
	//Exception handler to handle freelancer not found exception
	@ExceptionHandler(value = FreelancerNotFoundException.class)
	public ResponseEntity<ErrorModel> freelancerNotFound(FreelancerNotFoundException exception ){
		
		ErrorModel model= new ErrorModel(exception.getMessage());
		return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
	}
	
	//Exception handler to handle duplicate recruiter found exception
	@ExceptionHandler(value = DuplicateRecruiterFoundException.class)
	public ResponseEntity<ErrorModel> duplicateRecruiterFound(DuplicateRecruiterFoundException exception ) {
		
		ErrorModel model= new ErrorModel(exception.getMessage());
		return new ResponseEntity<ErrorModel>(model, HttpStatus.CONFLICT);
	}

	//Exception handler to handle duplicate freelancer found exception
	@ExceptionHandler(value = DuplicateFreelancerFoundException.class)
	public ResponseEntity<ErrorModel> duplicateDetailsFound(DuplicateFreelancerFoundException exception ) {
		
		 ErrorModel model= new ErrorModel(exception.getMessage());
		return new ResponseEntity<ErrorModel>(model, HttpStatus.CONFLICT);
	}
	
	//Exception handler to handle project not found exception
	@ExceptionHandler(value = ProjectNotFoundException.class)
	 public ResponseEntity<ErrorModel> projectNotFound(ProjectNotFoundException exception ) {
		
		ErrorModel model= new ErrorModel(exception.getMessage());
	   return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
	  }
	
	//Exception handler to handle recruiter not found exception
	@ExceptionHandler(value = RecruiterNotFoundException.class)
	 public ResponseEntity<ErrorModel> recruiterNotFound(RecruiterNotFoundException exception ) {
		
		ErrorModel model= new ErrorModel(exception.getMessage());
	   return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
	  }
	
	//Exception handler to handle project already exist exception
	@ExceptionHandler(value = ProjectAlreadyExistException.class)
	 public ResponseEntity<ErrorModel> duplicateProjectFound(ProjectAlreadyExistException exception ) {
		
		ErrorModel model= new ErrorModel(exception.getMessage());
	   return new ResponseEntity<ErrorModel>(model, HttpStatus.CONFLICT);
	  }
	
	//Exception handler to handle duplicate application exception
	@ExceptionHandler(value = DuplicateApplicationException.class)
	public ResponseEntity<ErrorModel> duplicateApplication(DuplicateApplicationException exception ){
		
		 ErrorModel model= new ErrorModel(exception.getMessage());	 
		return new ResponseEntity<ErrorModel>(model, HttpStatus.CONFLICT);
	}
	
	//Exception handler to handle already applied exception for application
	@ExceptionHandler(value = AlreadyAppliedException.class)
	public ResponseEntity<ErrorModel> alreadyApplied(AlreadyAppliedException exception ) {
		
		 ErrorModel model= new ErrorModel(exception.getMessage());
		return new ResponseEntity<ErrorModel>(model, HttpStatus.CONFLICT);
	}
	
	//Exception handler to handle application not found exception
	@ExceptionHandler(value = ApplicationNotFoundException.class)
	public ResponseEntity<ErrorModel> applicationNotFound(ApplicationNotFoundException exception ) {
		
		 ErrorModel model= new ErrorModel(exception.getMessage());	
		return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
	}
	
	//Exception handler to handle invalid status update exception
	@ExceptionHandler(value = InvalidStatusUpdateException.class)
	public ResponseEntity<ErrorModel> invalidStatusUpdate(InvalidStatusUpdateException exception ){
		
		 ErrorModel model= new ErrorModel(exception.getMessage());	
		return new ResponseEntity<ErrorModel>(model, HttpStatus.EXPECTATION_FAILED);
	}
	
	//Exception handler to handle not yet applied exception for application
	@ExceptionHandler(value = NotYetAppliedException.class)
	 public ResponseEntity<ErrorModel> notYetApplied(NotYetAppliedException exception ) {
		
		 ErrorModel model= new ErrorModel(exception.getMessage());	
		return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
	}
	
	//Exception handler to handle application closed exception
	@ExceptionHandler(value = ApplicationClosedException.class)
	 public ResponseEntity<ErrorModel> applicationClosed(ApplicationClosedException exception ) {

		  ErrorModel model= new ErrorModel(exception.getMessage());
		 return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_ACCEPTABLE);
	 }
	
	//Exception handler to handle application delete exception
	 @ExceptionHandler(value = ApplicationDeleteException.class)
	 public ResponseEntity<ErrorModel> applicationDelete(ApplicationDeleteException exception ) {
		 
		  ErrorModel model= new ErrorModel(exception.getMessage());
		 return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_ACCEPTABLE);
	 }
	 
	 
	 //Exception handler to handle validation relation exception
	 @ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
			
			Map<String, String> errorMap=new HashMap<>();
			exception.getBindingResult().getFieldErrors().forEach(error->{
				errorMap.put(error.getField(),error.getDefaultMessage());
			});
			return errorMap;
		}

   }
