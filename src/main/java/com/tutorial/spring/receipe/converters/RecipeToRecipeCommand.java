package com.tutorial.spring.receipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.model.Category;
import com.tutorial.spring.receipe.model.Ingredient;
import com.tutorial.spring.receipe.model.Recipe;

import lombok.Synchronized;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	private final NotesToNotesCommand notesConverter;
	private final IngredientsToIngredientsCommand ingredientsConverter;
	private final CategoryToCategoryCommand categoryConverter;
	
	/**
	 * 
	 * @param notesConverter
	 * @param ingredientsConverter
	 * @param categoryConverter
	 */
	public RecipeToRecipeCommand(NotesToNotesCommand notesConverter,
			IngredientsToIngredientsCommand ingredientsConverter, CategoryToCategoryCommand categoryConverter) {
		this.notesConverter = notesConverter;
		this.ingredientsConverter = ingredientsConverter;
		this.categoryConverter = categoryConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}
		final RecipeCommand command = new RecipeCommand();
		
		command.setId(source.getId());
		command.setDescription(source.getDescription());
		command.setPrepTime(source.getPrepTime());
		command.setCookTime(source.getCooktime());
		command.setServings(source.getServings());
		command.setSource(source.getSource());
		command.setUrl(source.getUrl());
		command.setDirections(source.getDirections());
		command.setDifficulty(source.getDifficulty());
		command.setNote(notesConverter.convert(source.getNote()));
		
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
		}
		
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			for (Ingredient ingredient : source.getIngredients()) {
					command.getIngredients().add(ingredientsConverter.convert(ingredient));
			}
		}
		
		return command;
	}

}
