/**
 * 
 */
package com.shabarish.boot.animals.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shabarish.boot.animals.model.AnimalResponseModel;

/**
 * @author shabarish
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		AnimalResponseModel response = new AnimalResponseModel();
        HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		response.setError(ex.getMessage());
		response.setStatus(errorStatus.toString());
		response.setMessage(ex.getMessage());
        return new ResponseEntity(response, errorStatus);
    }
 
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<AnimalResponseModel> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        AnimalResponseModel response = new AnimalResponseModel();
        HttpStatus errorStatus = HttpStatus.NOT_FOUND;
		response.setError(ex.getMessage());
		response.setStatus(errorStatus.toString());
		response.setMessage(ex.getMessage());
        return new ResponseEntity(response, errorStatus);
    }
    
    @ExceptionHandler(APIRequestException.class)
    public final ResponseEntity<AnimalResponseModel> handleRequestExceptions(APIRequestException ex, WebRequest request) {
    	AnimalResponseModel response = new AnimalResponseModel();
        HttpStatus errorStatus = HttpStatus.BAD_REQUEST;
		response.setError(ex.getMessage());
		response.setStatus(errorStatus.toString());
		response.setMessage(ex.getMessage());
        return new ResponseEntity<AnimalResponseModel>(response, errorStatus);
    }
    
    @ExceptionHandler(DAOException.class)
    public final ResponseEntity<AnimalResponseModel> handleDataAccessException(DAOException ex, WebRequest request) {
        AnimalResponseModel response = new AnimalResponseModel();
        HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		response.setError(ex.getMessage());
		response.setStatus(errorStatus.toString());
		response.setMessage(ex.getMessage());
        return new ResponseEntity(response, errorStatus);
    }
 
}
