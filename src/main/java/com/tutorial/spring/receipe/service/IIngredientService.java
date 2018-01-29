package com.tutorial.spring.receipe.service;

import java.util.Set;

import com.tutorial.spring.receipe.commands.IngredientsCommand;
import com.tutorial.spring.receipe.model.Ingredient;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IIngredientService {

	public IngredientsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	public IngredientsCommand saveIngredientCommand(IngredientsCommand command);
}
