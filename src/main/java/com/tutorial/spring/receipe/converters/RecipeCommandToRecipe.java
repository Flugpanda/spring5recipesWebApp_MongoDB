package com.tutorial.spring.receipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.CategoryCommand;
import com.tutorial.spring.receipe.commands.IngredientsCommand;
import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.model.Recipe;

import lombok.Synchronized;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

	private final NotesCommandToNotes notesConverter;
	private final CategoryCommandToCategory categoryConverter;
	private final IngredientsCommandToIngredients ingredientsConverter;
	
	/**
	 * 
	 * @param notesConverter
	 * @param categoryCommand
	 * @param ingredientsConverter
	 */
	public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryCommand,
			IngredientsCommandToIngredients ingredientsConverter) {
		this.notesConverter = notesConverter;
		this.categoryConverter = categoryCommand;
		this.ingredientsConverter = ingredientsConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		final Recipe recipe = new Recipe();
		
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setCooktime(source.getCooktime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setDirections(source.getDirections());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setNote(notesConverter.convert(source.getNote()));
		
		
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach((CategoryCommand categoryCommand) -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
		}
		
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			for (IngredientsCommand ingredientCommand : source.getIngredients()) {
				recipe.getIngredients().add(ingredientsConverter.convert(ingredientCommand));
			}
		}
		
		return recipe;
	}

}
