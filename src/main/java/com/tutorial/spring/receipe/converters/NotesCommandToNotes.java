package com.tutorial.spring.receipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tutorial.spring.receipe.commands.NotesCommand;
import com.tutorial.spring.receipe.model.Notes;

import lombok.Synchronized;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>{

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if (source == null) {
			return null;
		}
		final Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setContent(source.getContent());
		
		return notes;
	}

}
