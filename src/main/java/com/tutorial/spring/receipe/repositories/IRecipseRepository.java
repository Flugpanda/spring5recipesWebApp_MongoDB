package com.tutorial.spring.receipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.spring.receipe.model.Recipe;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Repository
public interface IRecipseRepository extends CrudRepository<Recipe, Long> {

}
