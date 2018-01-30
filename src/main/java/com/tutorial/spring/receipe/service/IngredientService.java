package com.tutorial.spring.receipe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorial.spring.receipe.commands.IngredientsCommand;
import com.tutorial.spring.receipe.converters.IngredientsCommandToIngredients;
import com.tutorial.spring.receipe.converters.IngredientsToIngredientsCommand;
import com.tutorial.spring.receipe.model.Ingredient;
import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.model.UnitOfMeasure;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;
import com.tutorial.spring.receipe.repositories.IUnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * the service for handling data of the ingredients
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
@Service
public class IngredientService implements IIngredientService {

	private final IRecipseRepository recipseRepository;
	private final IUnitOfMeasureRepository unitOfMeasureRepository;
	
	private final IngredientsToIngredientsCommand ingredientsToCommand;
	private final IngredientsCommandToIngredients commandToIngredients;

	/**
	 * default constructor
	 * 
	 * @param recipseRepository
	 * @param unitOfMeasureRepository
	 * @param ingredientsToCommand
	 * @param commandToIngredients
	 */
	public IngredientService(IRecipseRepository recipseRepository, IUnitOfMeasureRepository unitOfMeasureRepository,
			IngredientsToIngredientsCommand ingredientsToCommand, IngredientsCommandToIngredients commandToIngredients) {
		this.recipseRepository = recipseRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientsToCommand = ingredientsToCommand;
		this.commandToIngredients = commandToIngredients;
	}

	/**
	 * Search for a specific recipe from a recipe
	 */
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
				return ingredientsToCommand.convert(element);
			}
		}

		if (ingredientCommand == null) {
			log.debug(this.getClass().toString() + ":findByRecipeIdAndIngredientId - The ingrediten [" + ingredientId + "] was not found within the recipe [" + recipeId + "].");
			throw new IllegalArgumentException("The ingredient [" + ingredientId + "] was not found in the recipe [" + recipeId + "].");
		}

		return ingredientCommand;
	}

	/**
	 * Create or Update an ingredient 
	 */
	@Override
	public IngredientsCommand saveIngredientCommand(IngredientsCommand command) {

		// load the corresponding recipe from the db
		Optional<Recipe> recipeOptional = recipseRepository.findById(Long.valueOf(command.getRecipeId()));
		Recipe recipe = null;

		// check if the recipe did exist insde the db 
		if (!recipeOptional.isPresent()) {
			log.debug(this.getClass().toString() + ":saveIngredientCommand - The recipe with the id ["+ command.getRecipeId() + "] was not found of db.");
			throw new IllegalArgumentException("The recipe with the id [" + command.getRecipeId() + "] was not found of db.");
		} else {
			recipe = recipeOptional.get();
		}

		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

		// check if the ingredient already exists
		if (ingredientOptional.isPresent()) {
			// ingredient exists and has to be updated
			Ingredient ingredientToUpdate = ingredientOptional.get();
			
			ingredientToUpdate.setAmount(command.getAmount());
			ingredientToUpdate.setDescription(command.getDescription());
			
			// lookup the unit of measure from the db
			Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findById(command.getUnitOfMeas().getId());
			if (!uomOptional.isPresent()) {
				throw new IllegalArgumentException("The unit of measure  with the id ["	+ command.getUnitOfMeas().getId() + "] was not found of db.");
			}else {
				ingredientToUpdate.setUnitOfMeas(uomOptional.get());
			}
		} else {
			// ingredient doesn't exist -> add new Ingredient to recipe
			Ingredient ingredient = commandToIngredients.convert(command);
			ingredient.setRecipe(recipe);
			recipe.addIngredient(commandToIngredients.convert(command));
		}
		
		// persist the recipe and all its ingredients
		recipe = recipseRepository.save(recipe);
		
		// get the saved ingredient from the ingredient of the persisted recipe
		Optional<Ingredient> savedIngredientOptional = recipe.getIngredients().stream()
				.filter(recipeIngredient -> recipeIngredient.getId().equals(command.getId())).findFirst();
		
		// check if the ingredient is present after persisting
		// if there is no id set in the ingredient, look for the other values
		if (!savedIngredientOptional.isPresent()) {
			savedIngredientOptional = recipe.getIngredients().stream()
					.filter(recipeIngredient -> recipeIngredient.getDescription().equals(command.getDescription()))
					.filter(recipeIngredient -> recipeIngredient.getAmount().equals(command.getAmount()))
					.filter(recipeIngredient -> recipeIngredient.getUnitOfMeas().getId().equals(command.getUnitOfMeas().getId()))
					.findFirst();
		}
		
		if (!savedIngredientOptional.isPresent()) {
			throw new RuntimeException("Not able to save the IngredientCommand: " + command.toString());
		}
		
		return ingredientsToCommand.convert(savedIngredientOptional.get());
	}

	/**
	 * 
	 */
	@Override
	public void deleteIngredientFromRecipe(Long recipeId, Long ingredientId) {
		
		Recipe recipe;
		Ingredient ingredientToDelte;
		Optional<Ingredient> ingredientOptional;
		Optional<Recipe> recipeOptional = recipseRepository.findById(recipeId);
		
		// check if the recipe exists
		if (!recipeOptional.isPresent()) {
			throw new IllegalArgumentException("The recipe  with the id ["	+ recipeId + "] was not found of db.");
		}else {
			recipe = recipeOptional.get();
		}
	
		// try to the ingredient from the recipe
		ingredientOptional = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
		
		// check if the ingredient exists
		if (!ingredientOptional.isPresent()) {
			throw new IllegalArgumentException("The recipe  with the id ["	+ recipeId + "] does not contain the ingredient with the id [" + ingredientId + "].");
		}else {
			log.debug(this.getClass().toString() + ":deleteIngredientFromRecipe - Deleting the ingredient [" + ingredientId + "] from the recipe [" + recipeId + "].");
			
			// get the actual ingredient object
			ingredientToDelte = ingredientOptional.get();
			
			// remove the ingredient from the recipe
			recipe.getIngredients().remove(ingredientToDelte);
			
			// Delete the ingredient
			// Setting the value null will tell hibernate to remove the object from the db,
			// because the object has no longer a valid many to one relationship to a recipe 
			ingredientToDelte.setRecipe(null);
			
			// persist the recipe
			recipseRepository.save(recipe);
		}
	}

}
