package com.tutorial.spring.receipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tutorial.spring.receipe.model.Ingredient;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IIngredientReoisitory extends CrudRepository<Ingredient, Long> {

}
