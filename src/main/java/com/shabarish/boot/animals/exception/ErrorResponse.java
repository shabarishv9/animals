/**
 * 
 */
package com.shabarish.boot.animals.exception;

import java.util.List;

/**
 * @author shabarish
 *
 */
public class ErrorResponse {

	 private String message;
	 private List<String> details;
	 
	public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
   
}
