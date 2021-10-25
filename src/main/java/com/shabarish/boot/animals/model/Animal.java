/**
 * 
 */
package com.shabarish.boot.animals.model;

/**
 * @author shabarish
 *
 */
public class Animal {
	
	private String type;
	private String movement;
	private String name;
	
	public Animal() {}
	
	public Animal(String type, String movement, String name) {
		super();
		this.type = type;
		this.movement = movement;
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Animal [type=" + type + ", movement=" + movement + ", name=" + name + "]";
	}
	
	

}
