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
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DAOException extends RuntimeException  {
	
	public DAOException(String exception) {
        super(exception);
    }
}
