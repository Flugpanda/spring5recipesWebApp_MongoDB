package com.tutorial.spring.receipe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorial.spring.receipe.commands.IngredientsCommand;
import com.tutorial.spring.receipe.converters.IngredientsToIngredientsCommand;
import com.tutorial.spring.receipe.model.Ingredient;
import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
@Service
public class IngredientService implements IIngredientService {

	private final IRecipseRepository recipseRepository;
	private final IngredientsToIngredientsCommand ingredientsToIngredientsCommand;
	

	/**
	 * defualt constructor
	 * 
	 * @param recipseRepository
	 * @param ingredientsToIngredientsCommand
	 */
	public IngredientService(IRecipseRepository recipseRepository,
			IngredientsToIngredientsCommand ingredientsToIngredientsCommand) {
		this.recipseRepository = recipseRepository;
		this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
	}

	
	@Override
	public IngredientsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> recipeOptional = recipseRepository.findById(recipeId);
		
		if (!recipeOptional.isPresent()) {
			throw new IllegalArgumentException("The recipe with the id [" + recipeId + "] was not found.");
		}
		
		Recipe recipe = recipeOptional.get();
		IngredientsCommand ingredientCommand = null;
		
		for (Ingredient element : recipe.getIngredients()) {
			if (element.getId() == ingredientId) {
				return ingredientsToIngredientsCommand.convert(element);
			}
		}
				
		if (ingredientCommand == null) {
			log.debug(this.getClass().toString() + ":findByRecipeIdAndIngredientId - The ingrediten [" + ingredientId + "] was not found within the recipe [" + recipeId + "].");
			throw new IllegalArgumentException("The ingredient [" + ingredientId + "] was not found in the recipe [" + recipeId + "]."); 
		}
		
		return ingredientCommand;
	}

}
