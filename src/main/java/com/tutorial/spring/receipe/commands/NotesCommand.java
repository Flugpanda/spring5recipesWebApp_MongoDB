package com.tutorial.spring.receipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private RecipeCommand recipe;
	private String content;
}
