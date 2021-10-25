/**
 * 
 */
package com.shabarish.boot.animals.controller;

import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shabarish.boot.animals.exception.RecordNotFoundException;
import com.shabarish.boot.animals.model.Animal;
import com.shabarish.boot.animals.model.AnimalRequestModel;
import com.shabarish.boot.animals.model.AnimalResponseModel;
import com.shabarish.boot.animals.service.AnimalService;
/**
 * @author shabarish
 *
 */
@RestController
@RequestMapping("/api")
public class AnimalController implements AnimalAPIInterfaceDelegate {
	
	@Autowired
	private AnimalService animalService;
	
	/* (non-Javadoc)
	 * @see com.shabarish.boot.animals.controller.AnimalAPIInterfaceDelegate#getAnimals(java.lang.String)
	 */
	@Override
	@RequestMapping(value="/animals/move/{move}",produces= {"application/json"},method=RequestMethod.GET)
	public ResponseEntity<AnimalResponseModel> getAnimals(@PathVariable String move) {
		ResponseEntity<AnimalResponseModel> res = animalService.getAnimals(move);
		List<Animal> data = res.getBody().getData();
		if(data.size() == 0) {
			throw new RecordNotFoundException(" No animals found for movement type :  "+move);
		}
			 return animalService.getAnimals(move);
	}
	
	/* (non-Javadoc)
	 * @see com.shabarish.boot.animals.controller.AnimalAPIInterfaceDelegate#deleteAnimalByName(java.lang.String)
	 */
	@Override
	@RequestMapping(value="/animals/{name}",method=RequestMethod.DELETE)
	public ResponseEntity<AnimalResponseModel> deleteAnimalByName(@PathVariable String name) {
		return animalService.deleteAnimal(name);
	}
	
	/* (non-Javadoc)
	 * @see com.shabarish.boot.animals.controller.AnimalAPIInterfaceDelegate#addAnimals(com.shabarish.boot.animals.model.AnimalRequestModel)
	 */
	@Override
	@RequestMapping(value="/animals",produces= {"application/json"},consumes= {"application/json"},method=RequestMethod.POST)
	public ResponseEntity<AnimalResponseModel> addAnimals(@RequestBody AnimalRequestModel reqModel) {
		return animalService.addAnimals(reqModel);
	}

}
