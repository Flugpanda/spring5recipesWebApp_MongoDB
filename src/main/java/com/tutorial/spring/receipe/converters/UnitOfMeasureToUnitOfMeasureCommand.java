package com.tutorial.spring.receipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.UnitOfMeasureCommand;
import com.tutorial.spring.receipe.model.UnitOfMeasure;

import lombok.Synchronized;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if (source == null) {
			return null;
		}
		final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(source.getId());
		command.setUnitDescription(source.getUom());
		
		return command;
	}

}
