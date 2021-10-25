/**
 * 
 */
package com.shabarish.boot.animals.controller;

import org.springframework.http.ResponseEntity;

import com.shabarish.boot.animals.model.AnimalRequestModel;
import com.shabarish.boot.animals.model.AnimalResponseModel;

/**
 * @author shabarish
 *
 */
public interface AnimalAPIInterfaceDelegate {

	ResponseEntity<AnimalResponseModel> getAnimals(String move);

	ResponseEntity<AnimalResponseModel> deleteAnimalByName(String name);

	ResponseEntity<AnimalResponseModel> addAnimals(AnimalRequestModel reqModel);

}
