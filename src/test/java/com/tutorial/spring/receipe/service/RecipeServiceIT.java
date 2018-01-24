package com.tutorial.spring.receipe.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.converters.RecipeCommandToRecipe;
import com.tutorial.spring.receipe.converters.RecipeToRecipeCommand;
import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

/**
 * Integration test for the RecipeService
 * 
 * @SpringBootTest		will bring up the whole spring context 
 * 
 * @author Bastian Bräunel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

	public static final String NEW_DESCRIPTION = "A whole new description";

	@Autowired
	RecipeService recipeService;

	@Autowired
	IRecipseRepository recipeRepository;

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Transactional
	@Test
	public void testSaveOfDescription() throws Exception {
		// given
			// get all recipes from the db
		Iterable<Recipe> recipes = recipeRepository.findAll();
			// get the first recipe from the set of all recipes
		Recipe testRecipe = recipes.iterator().next();
			// convert the Recipe to a RecipeCommand object
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

		// when
			// change the description of the RecipeCommand
		testRecipeCommand.setDescription(NEW_DESCRIPTION);
			// persist the RecipeCommand
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

		// then
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
		assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}
}
