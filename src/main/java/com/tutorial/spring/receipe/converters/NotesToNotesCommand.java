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
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{

	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes source) {
		if (source == null) {
			return null;
		}
		final NotesCommand command = new NotesCommand();
		command.setId(source.getId());
		command.setContent(source.getContent());
		
		return command;
	}

}
