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
public class IngredientsToIngredientsCommand implements Converter<Ingredient, IngredientsCommand>{

	private final UnitOfMeasureToUnitOfMeasureCommand unitCoverter;
	
	/**
	 * default constructor 
	 * 
	 * @param ingredientConverter
	 */
	public IngredientsToIngredientsCommand(UnitOfMeasureToUnitOfMeasureCommand unitCoverter) {
		this.unitCoverter = unitCoverter;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientsCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		final IngredientsCommand command = new IngredientsCommand();
		command.setId(source.getId());
		command.setAmount(source.getAmount());
		command.setDescription(source.getDescription());
		command.setUnitOfMeas(unitCoverter.convert(source.getUnitOfMeas()));
		
		return command;
	}

}
