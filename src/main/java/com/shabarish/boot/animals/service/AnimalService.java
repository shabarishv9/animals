/**
 * 
 */
package com.shabarish.boot.animals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shabarish.boot.animals.cache.CacheSrevice;
import com.shabarish.boot.animals.exception.APIRequestException;
import com.shabarish.boot.animals.exception.RecordNotFoundException;
import com.shabarish.boot.animals.model.Animal;
import com.shabarish.boot.animals.model.AnimalRequestModel;
import com.shabarish.boot.animals.model.AnimalResponseModel;
import com.shabarish.boot.animals.repo.AnimalRepository;

/**
 * @author shabarish
 *
 */

@Service
public class AnimalService implements AnimalAPIServiceDelegate {

	@Autowired 
	private AnimalRepository animalRepo;
	
	@Autowired
	private CacheSrevice cacheService;
	
	@Autowired
	private AnimalServiceHelper helper;
	
	/* (non-Javadoc)
	 * @see com.shabarish.boot.animals.service.AnimalAPIDelegate#getAnimals(java.lang.String)
	 */
	@Override
	public ResponseEntity<AnimalResponseModel> getAnimals(String move) {
		if( move!=null && move == "") {
			throw new APIRequestException(" Animal attribures move canot be empty : ");
		}
		AnimalResponseModel response = new AnimalResponseModel();
		try {
			response.setData(animalRepo.findByMove(move));
			response.setMessage("Animals data received");
			response.setStatus(HttpStatus.OK.toString());
			return new ResponseEntity<AnimalResponseModel>(response,HttpStatus.OK);
		} catch(Exception e) {
			HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setError(e.getMessage());
			response.setStatus(errorStatus.toString());
			response.setMessage("Error while accessing movement : "+move);
			return new ResponseEntity<AnimalResponseModel>(response,errorStatus);
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.shabarish.boot.animals.service.AnimalAPIDelegate#deleteAnimal(java.lang.String)
	 */
	@Override
	public ResponseEntity<AnimalResponseModel> deleteAnimal(String name) {
		if( name!=null && name == "") {
			throw new APIRequestException(" Animal attribures name canot be empty : ");
		}
		AnimalResponseModel response = new AnimalResponseModel();
		try {
			animalRepo.deleteAnimal(name);
			response.setMessage("Animal with name: "+name+ " deleted");
			response.setStatus(HttpStatus.OK.toString());
			// Need to remove from cache
			cacheService.removeElementFromCache(name);
			return new ResponseEntity<AnimalResponseModel>(response,HttpStatus.OK);
		} catch(Exception e) {
			HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setError(e.getMessage());
			response.setStatus(errorStatus.toString());
			response.setMessage("Error while deleting animal : "+name);
			return new ResponseEntity<AnimalResponseModel>(response,errorStatus);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.shabarish.boot.animals.service.AnimalAPIDelegate#addAnimals(com.shabarish.boot.animals.model.AnimalRequestModel)
	 */
	@Override
	public ResponseEntity<AnimalResponseModel> addAnimals(AnimalRequestModel reqModel) {
		AnimalResponseModel response = new AnimalResponseModel();
			//validation
			checkIfAnimalExists(reqModel);
			
			Animal animal = helper.getAnimalSubType(reqModel.getType());
			animal.setMovement(reqModel.getMove());
			animal.setName(reqModel.getName());
			animal.setType(reqModel.getType());
			
			animalRepo.addAnimals(animal);
			response.setMessage("Animal added "+animal);
			response.setStatus(HttpStatus.CREATED.toString());
			// Need to add to cache
			Animal animalCache = new Animal();
			animalCache.setMovement(reqModel.getName());
			animalCache.setMovement(reqModel.getMove());
			animalCache.setType(reqModel.getType());
			cacheService.addElementInCache(animalCache.getName(), animalCache);
			return new ResponseEntity<AnimalResponseModel>(response,HttpStatus.CREATED);
	}

	private void checkIfAnimalExists(AnimalRequestModel reqModel) {
		String move = reqModel.getMove();
		String type = reqModel.getType();
		String name = reqModel.getName();
		
		if((name!=null &&name== "") || (type !=null && type == "") || ( move!=null && move == "")) {
			throw new APIRequestException(" Animal attribures canot be empty : "+reqModel);
		}
		List<Animal> animals = animalRepo.findByMoveAndType(move,type);
		boolean animalFound = animals.stream().anyMatch(a-> a.getName().equals(name));
		if(animalFound) {
			throw new APIRequestException(" Animal name:  "+name+" already present for the combination "+reqModel);
		}
	}

}
