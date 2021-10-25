/**
 * 
 */
package com.shabarish.boot.animals.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/**
 * @author shabarish
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class APIRequestException extends RuntimeException  {
	
	public APIRequestException(String exception) {
        super(exception);
    }
}
