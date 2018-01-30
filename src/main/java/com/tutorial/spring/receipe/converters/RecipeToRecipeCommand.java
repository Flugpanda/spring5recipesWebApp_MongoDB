package com.tutorial.spring.receipe.converters;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.IngredientsCommand;
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
		command.setImage(source.getImage());
		command.setNote(notesConverter.convert(source.getNote()));
		
		// check if any category is set
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
		}
		
		// check if any ingredient is set
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			
			// creating a small comparator to output a sorted set of IngredientsCommands for the view
			Set<IngredientsCommand> sortedIngredients = new TreeSet<>(new Comparator<IngredientsCommand>() {
				@Override
				public int compare(IngredientsCommand o1, IngredientsCommand o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});
			
			for (Ingredient ingredient : source.getIngredients()) {
				// add the converted item so the sorted set
				sortedIngredients.add(ingredientsConverter.convert(ingredient));
			}
			// assign the set of the sorted IngredientsCommands to the command object 
			command.setIngredients(sortedIngredients);
		}
		
		return command;
	}

}
