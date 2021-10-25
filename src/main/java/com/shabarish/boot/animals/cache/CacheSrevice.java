/**
 * 
 */
package com.shabarish.boot.animals.cache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shabarish.boot.animals.model.Animal;

/**
 * @author shabarish
 *
 */
@Service
public class CacheSrevice {
	private static Deque<String> q = new LinkedList<>();
	private static Map<String, Cache> map = new HashMap<>();
	public int CACHE_CAPACITY = 20;

	public Animal getElementFromCache(String name) {

		if (map.containsKey(name)) {
			Cache cur = map.get(name);
			q.remove(cur.keyName);
			q.addFirst(cur.keyName);
			return cur.value;
		}
		return null;
	}
	public void removeElementFromCache(String key) {
		if (map.containsKey(key)) {
			Cache cur = map.get(key);
			q.remove(cur.keyName);
		}
	}
	public void addElementInCache(String key, Animal value) {
		if (map.containsKey(key)) {
			Cache cur = map.get(key);
			q.remove(cur.keyName);
		} else {
			if (q.size() == CACHE_CAPACITY) {
				String tempKey = q.removeLast();
				map.remove(tempKey);
			}
		}
		Cache newEle = new Cache(key, value);
		q.addFirst(newEle.keyName);
		map.put(key, newEle);
	}
	
	/*public static void main(String [] arg) {
		CacheSrevice c = new CacheSrevice();
		Animal a1 = new Animal();
		a1.setMovement("walk");
		a1.setName("shabarish1");
		a1.setType("human");
		c.addElementInCache(a1.getName(), a1);
		
		Animal a2 = new Animal();
		a2.setMovement("walk");
		a2.setName("shabarish2");
		a2.setType("human");
		c.addElementInCache(a2.getName(), a2);
		
		Animal a3 = new Animal();
		a3.setMovement("walk");
		a3.setName("shabarish3");
		a3.setType("human");
		c.addElementInCache(a3.getName(), a3);
		
		System.out.println("==========================\n");
		System.out.println(" Initial QUEUE "+q );
		System.out.println("\n==========================\n");
		
		
		c.getElementFromCache("shabarish2");
		System.out.println("==========================\n");
		System.out.println(" QUEUE "+q );
		System.out.println("\n==========================\n");
	}*/
}

class Cache {
	String keyName;
	Animal value;

	Cache(String key, Animal value) {
		this.keyName = key;
		this.value = value;
	}
}