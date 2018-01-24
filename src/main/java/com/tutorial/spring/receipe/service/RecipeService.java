package com.tutorial.spring.receipe.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

import net.bytebuddy.asm.Advice.This;

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
		try {
			// Iterate over every item that was found on the database and add it to the set
			recipseRepository.findAll().iterator().forEachRemaining(recipes::add);
		} catch (Exception e) {
			System.err.println(This.class.toString() + ": Recipes not found." );
			System.err.println(e.getStackTrace());
		}
		return recipes;
	}

	/**
	 * Find a specific recipe by its id
	 * 
	 * @param id	the id of the recipe
	 * @return		the recipe from the db
	 */
	@Override
	public Recipe findById(Long id) {
		Recipe recipe = new Recipe();
		recipe.setId(-1l);
		recipe.setDescription("Recipe not found");
		
		try {
			recipe = recipseRepository.findById(id).get();
		} catch (Exception e) {
			System.err.println(This.class.toString() + ": Recipe with the id [" + id + "] was not found." );
			System.err.println("\t" + e.getMessage());
		}
		return recipe;
	}

}
