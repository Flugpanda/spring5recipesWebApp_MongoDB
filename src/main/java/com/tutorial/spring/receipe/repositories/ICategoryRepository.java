package com.tutorial.spring.receipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tutorial.spring.receipe.model.Category;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface ICategoryRepository extends CrudRepository<Category, String> {

	/**
	 * The method is used to get the data using a jpa query method.
	 * 
	 * @param description	the description of the category you are looking for
	 * 
	 * @return		the object from the database
	 */
	Optional<Category> findByDescription(String description);
}
