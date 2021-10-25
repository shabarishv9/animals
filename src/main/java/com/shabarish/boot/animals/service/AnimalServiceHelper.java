/**
 * 
 */
package com.shabarish.boot.animals.service;

import org.springframework.stereotype.Service;

import com.shabarish.boot.animals.model.Animal;
import com.shabarish.boot.animals.model.Bird;
import com.shabarish.boot.animals.model.Fish;
import com.shabarish.boot.animals.model.Human;
import com.shabarish.boot.animals.model.Kangaroo;
import com.shabarish.boot.animals.model.Snake;

/**
 * @author shabarish
 *
 */
@Service
public class AnimalServiceHelper {

	public Animal getAnimalSubType(String type) {
		
		switch (type) {
		case "Bird":
			return new Bird();
		case "Fish":
			return new Fish();
		case "Human":
			return new Human();
		case "Snake":
			return new Snake();
		case "Kangaroo":
			return new Kangaroo();
		default:
			throw new IllegalArgumentException("Invalid Animal Type : " + type);
		}

	}
}
