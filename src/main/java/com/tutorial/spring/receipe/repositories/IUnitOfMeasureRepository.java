package com.tutorial.spring.receipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tutorial.spring.receipe.model.UnitOfMeasure;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IUnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String>{

	/**
	 * The method is used to get the data using a jpa query method.
	 * 
	 * @param description	the description of the unit of measurement you are looking for
	 * 
	 * @return		the object from the database
	 */
	Optional<UnitOfMeasure> findByUnitDescription(String description);
}
