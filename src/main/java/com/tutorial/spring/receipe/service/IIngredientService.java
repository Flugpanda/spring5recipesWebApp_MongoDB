package com.tutorial.spring.receipe.service;

import com.tutorial.spring.receipe.commands.IngredientsCommand;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IIngredientService {

	public IngredientsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
