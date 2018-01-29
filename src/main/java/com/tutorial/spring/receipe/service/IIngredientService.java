package com.tutorial.spring.receipe.service;

import com.tutorial.spring.receipe.commands.IngredientsCommand;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IIngredientService {

	public IngredientsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	public IngredientsCommand saveIngredientCommand(IngredientsCommand command);
	
	public void deleteIngredientFromRecipe(Long recipeId, Long ingredientId);
}
