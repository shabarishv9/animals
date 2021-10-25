/**
 * 
 */
package com.shabarish.boot.animals.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.shabarish.boot.animals.model.AnimalRequestModel;
import com.shabarish.boot.animals.model.AnimalResponseModel;

/**
 * @author shabarish
 *
 */
public interface AnimalAPIServiceDelegate {

	ResponseEntity<AnimalResponseModel> getAnimals(String move);

	ResponseEntity<AnimalResponseModel> deleteAnimal(String name);

	ResponseEntity<AnimalResponseModel> addAnimals(AnimalRequestModel reqModel);
}
