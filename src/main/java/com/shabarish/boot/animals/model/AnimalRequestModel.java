/**
 * 
 */
package com.shabarish.boot.animals.model;

/**
 * @author shabarish
 *
 */
public class AnimalRequestModel {

	private String name;
	private String type;
	private String move;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMove() {
		return move;
	}
	public void setMove(String move) {
		this.move = move;
	}
	@Override
	public String toString() {
		return "AnimalRequestModel [name=" + name + ", type=" + type + ", move=" + move + "]";
	}
	
	
}
