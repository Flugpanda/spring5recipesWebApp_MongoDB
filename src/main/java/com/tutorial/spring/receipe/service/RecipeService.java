package com.tutorial.spring.receipe.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

/**
 * This Service is made to load all the recipes from the database
 * 
 * @author Bastian Bräunel
 *
 */
@Service
public class RecipeService implements IRecipeService {

	private final IRecipseRepository recipseRepository;
	
	/**
	 * 
	 * Constructor based injection of the repository
	 * 
	 * @param recipseRepository
	 */
	public RecipeService(IRecipseRepository recipseRepository) {
		this.recipseRepository = recipseRepository;
	}

	/**
	 * Find all recipes and add the to a list
	 */
	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		// Iterate over every item that was found on the database and add it to the set
		recipseRepository.findAll().iterator().forEachRemaining(recipes::add);
		return recipes;
	}

}
