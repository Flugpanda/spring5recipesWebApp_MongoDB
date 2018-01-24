package com.tutorial.spring.receipe.service;

import java.util.Set;

import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.model.Recipe;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public interface IRecipeService {
	
	public Set<Recipe> getRecipes();
	public Recipe findById(Long id);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
