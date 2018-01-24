package com.tutorial.spring.receipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.UnitOfMeasureCommand;
import com.tutorial.spring.receipe.model.UnitOfMeasure;

import lombok.Synchronized;

/**
 * This class converts an object from UnitOfMeasureCommand to UnitOfMeasure
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if (source == null) {
			return null;
		}
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setUom(source.getUnitDescription());
		
		return uom;
	}

}
