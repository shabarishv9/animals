/**
 * 
 */
package com.shabarish.boot.animals.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shabarish.boot.animals.model.Animal;
import com.shabarish.boot.animals.repo.AnimalRepository;

/**
 * @author shabarish
 *
 */
@Component
public class CacheInit {

	@Autowired
	private CacheSrevice cacheService;
	
	@Autowired 
	private AnimalRepository animalRepo;
	
	@PostConstruct
	private void cacheLoad() {
		
		List<Animal> animals = animalRepo.findAllAnimals();
		if(animals.size()>0) {
			for(int index=0;index<cacheService.CACHE_CAPACITY-1;index++) {
				Animal animal = animals.get(index);
				cacheService.addElementInCache(animal.getName(), animal);
			}
		}
		System.out.println(" cache loaded during start up .... ");
	}
	
}
