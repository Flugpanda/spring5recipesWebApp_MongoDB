	package com.tutorial.spring.receipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.IngredientsCommand;
import com.tutorial.spring.receipe.model.Ingredient;

import lombok.Synchronized;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class IngredientsCommandToIngredients implements Converter<IngredientsCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
	
	/**
	 * default Constructor
	 * 
	 * @param converter
	 */
	public IngredientsCommandToIngredients(UnitOfMeasureCommandToUnitOfMeasure converter) {
		this.uomConverter = converter;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientsCommand source) {
		if (source == null) {
			return null;
		}
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());
		ingredient.setUnitOfMeas(uomConverter.convert(source.getUnitOfMeas()));
		
		return ingredient;
	}

}
