/**
 * 
 */
package com.shabarish.boot.animals.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.shabarish.boot.animals.exception.DAOException;
import com.shabarish.boot.animals.model.Animal;
import com.shabarish.boot.animals.model.AnimalRequestModel;

/**
 * @author shabarish
 *
 */
@Repository
public class AnimalRepository {
	
	private static final String INSERT_INTO_ANIMALS = "INSERT INTO ANIMALS (NAME,TYPE,MOVEMENT) values(?, ?, ?)";

	private static final String DELETE_ANIMALS = "DELETE FROM ANIMALS WHERE name=?";
	
	private static final String SELECT_ALL_ANIMALS = "SELECT a.name as name,tm.type as type,tm.movement as movement "
			+ "FROM ANIMALS  a join type_movement tm on a.movement = tm.id";

	private static final String SELECT_ANIMALS = "SELECT a.name as name,tm.type as type,tm.movement as movement "
			+ "FROM ANIMALS  a join type_movement tm on a.movement = tm.id where tm.movement=?";
	
	private static final String SELECT_ANIMALS_VALIDATE_QRY="SELECT a.name as name,tm.type as type,tm.movement as movement"
			+ " FROM ANIMALS  a join type_movement tm on a.movement = tm.id where tm.movement=? and tm.type=?";

	private static final String SELECT_TYPE = "select id from type_movement where type=?";
	private static final String SELECT_MOVEMENT = "select id from type_movement where movement=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Animal> findAllAnimals(){
		
		List<Animal> animals = new ArrayList<>();
		try {
			animals =  jdbcTemplate.query(SELECT_ALL_ANIMALS,new BeanPropertyRowMapper(Animal.class));
			
		}  catch (DataAccessException e) {
			throw new DAOException(" Internal error occured ");
		}  
		catch(Exception e) {
			throw e;
		}
		return animals;
	}
	
	public List<Animal> findByMove(String move){
		
		List<Animal> animals = new ArrayList<>();
		try {
			animals =  jdbcTemplate.query(SELECT_ANIMALS,new Object[] { move },	new BeanPropertyRowMapper(Animal.class));
			
		}  catch (DataAccessException e) {
			throw new DAOException(" Internal error occured ");
		}  
		catch(Exception e) {
			throw e;
		}
		return animals;
	}
	
	public List<Animal> findByMoveAndType(String move,String type){
			
			List<Animal> animals = new ArrayList<>();
			try {
				//animals =  jdbcTemplate.query("select * from animals where movement=? ",new Object[] { move }, (rs,rowNum)-> 
				//new Animal(String.valueOf(rs.getInt("type")),String.valueOf(rs.getInt("movement")),rs.getString("name")));
				//working
				animals =  jdbcTemplate.query(SELECT_ANIMALS_VALIDATE_QRY,new Object[] { move,type }, 
						new BeanPropertyRowMapper(Animal.class));
				
			} catch (DataAccessException e) {
				throw new DAOException(" Internal error occured ");
			} catch(Exception e) {
				throw e;
			}
			return animals;
		}

	public void deleteAnimal(String name) {
		try {
			jdbcTemplate.update(DELETE_ANIMALS,new Object[] { name });
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException(" Internal error occured ");
		} 
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addAnimals(Animal animal) {
		try {
			int type = getTypeData(animal.getType());
			int move = getMovementData(animal.getMovement());
			
			jdbcTemplate.update(INSERT_INTO_ANIMALS	,new Object[] { animal.getName(),type,move });
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DAOException(" Internal error occured ");
		} 
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	private Integer getTypeData(String type) {
		Integer typeId = 0;
		try {
			 typeId = jdbcTemplate.queryForObject(SELECT_TYPE,new Object[] { type },	Integer.class);
			
		}  catch (DataAccessException e) {
			throw new DAOException(" Error !! Type entered might not be present:  "+type);
		} 
		catch(Exception e) {
			throw e;
		}
		return typeId;
	}

	private int getMovementData(String move) {
		Integer movementId = 0;
		try {
			 movementId = jdbcTemplate.queryForObject(SELECT_MOVEMENT,new Object[] { move },	Integer.class);
			
		}  catch (DataAccessException e) {
			throw new DAOException(" Error !! movement entered might not be present:  "+move);
		} 
		catch(Exception e) {
			throw e;
		}
		return movementId;
	}


}
