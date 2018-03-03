package com.tutorial.spring.receipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tutorial.spring.receipe.model.Recipe;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IRecipseRepository extends CrudRepository<Recipe, String> {

}
