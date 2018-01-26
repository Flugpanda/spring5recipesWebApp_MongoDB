package com.tutorial.spring.receipe.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.converters.RecipeCommandToRecipe;
import com.tutorial.spring.receipe.converters.RecipeToRecipeCommand;
import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;

/**
 * This Service is made to load all the recipes from the database
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
@Service
public class RecipeService implements IRecipeService {

	private final IRecipseRepository recipseRepository;
	
	private final RecipeCommandToRecipe commandToRecipe;
	private final RecipeToRecipeCommand recipeToCommand;

	/**
	 * 
	 * Constructor based injection of the repository
	 * 
	 * @param recipseRepository
	 */
	public RecipeService(IRecipseRepository recipseRepository, RecipeCommandToRecipe commandToRecipe, RecipeToRecipeCommand recipeToCommand) {
		this.recipseRepository = recipseRepository;
		this.commandToRecipe = commandToRecipe;
		this.recipeToCommand = recipeToCommand;
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
		Optional<Recipe> recipe = recipseRepository.findById(id);
		
		if (!recipe.isPresent()) {
			throw new IllegalAccessError("The recipe with the id [" + id + "] was not found in the database.");
		}
		
		return recipe.get();
	}

	/**
	 * Convert a RecipeCommand to a Recipe object and persist it
	 * 
	 * @param recipeCommand		the command object of the recipe
	 */
	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe detatchedRecipe = commandToRecipe.convert(recipeCommand);
		
		detatchedRecipe = recipseRepository.save(detatchedRecipe);
		log.debug(this.getClass().toString() +":saveRecipeCommand - Saved recipe with id [" + detatchedRecipe.getId() + "]");
				
		return recipeToCommand.convert(detatchedRecipe);
	}

	/**
	 * Look up a RecipeCommand by id
	 * 
	 * @return		The RecipeCommand with the given id
	 */
	@Override
	@Transactional
	public RecipeCommand findRecipeCommandById(Long id) {
		RecipeCommand command = recipeToCommand.convert(findById(id));
		
		return command;
	}

	/**
	 * Delete a recipe from the database
	 */
	@Override
	public void deleteById(Long id) {
		recipseRepository.deleteById(id);		
	}	
}
