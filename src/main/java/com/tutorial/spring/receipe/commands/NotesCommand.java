package com.tutorial.spring.receipe.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A JavaBean which will hold all the data for a Notes object from the forms in
 * the views.
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private String id;
	private RecipeCommand recipe;

	@NotBlank
	@Size(min = 3, max = 255)
	private String content;
}
