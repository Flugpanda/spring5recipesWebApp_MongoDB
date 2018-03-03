package com.tutorial.spring.receipe.service;

import com.tutorial.spring.receipe.commands.IngredientsCommand;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IIngredientService {

	public IngredientsCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
	public IngredientsCommand saveIngredientCommand(IngredientsCommand command);
	
	public void deleteIngredientFromRecipe(String recipeId, String ingredientId);
}
