package com.shabarish.boot.animals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shabarish.boot.animals.controller.AnimalController;
import com.shabarish.boot.animals.exception.APIRequestException;
import com.shabarish.boot.animals.model.Animal;
import com.shabarish.boot.animals.model.AnimalRequestModel;
import com.shabarish.boot.animals.model.AnimalResponseModel;
import com.shabarish.boot.animals.service.AnimalService;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AnimalsApplicationTests {

	@Autowired
	AnimalService service;
	
	@Autowired
	AnimalController controller;
	
	@Test
	public void testGetanimals() {
		ResponseEntity<AnimalResponseModel> res = service.getAnimals("move");
		assertEquals(res.getBody().getMessage().contains("Animals data received"),true);
	}
	
	// test failure case
	@Test
	public void testGetanimals_Failure() {
		Throwable exception = assertThrows(APIRequestException.class, () -> {
			service.getAnimals("");
		});
	}


	@Test
	public void testAddAnimals() {
		AnimalRequestModel animal = new AnimalRequestModel();
		animal.setMove("Walk");
		animal.setName("name1");
		animal.setType("Human");
		ResponseEntity<AnimalResponseModel> res = service.addAnimals(animal);
		assertEquals(res.getBody().getStatus().contains(HttpStatus.CREATED.toString()),true);
		
	}
	// test failure case
	@Test
	public void testAddAnimals_FAILURE() {
		AnimalRequestModel animal = new AnimalRequestModel();
		animal.setMove("Walk");
		animal.setName("");
		animal.setType("Human");
		Throwable exception = assertThrows(APIRequestException.class, () -> {
			service.addAnimals(animal);
		});
		
	}
	
	@Test
	public void testDeleteanimals() {
		ResponseEntity<AnimalResponseModel> res = service.deleteAnimal("test");
		assertEquals(res.getBody().getMessage().contains(HttpStatus.OK.toString()),false);
		
	}
	// Test failure case
	@Test
	public void testDeleteanimals_FAILURE() {
		Throwable exception = assertThrows(APIRequestException.class, () -> {
			service.deleteAnimal("");
		});
	}
}
