package com.tutorial.spring.receipe.commands;

import java.util.HashSet;
import java.util.Set;

import com.tutorial.spring.receipe.model.enums.EDifficulty;

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
public class RecipeCommand {

	private Long id;
	private String description;
	private String prepTime;
	private String cookTime;
	private String servings;
	private String source;
	private String url;
	private String directions;
	private Byte[] image;
	private EDifficulty difficulty;
	private NotesCommand note;	
	private Set<IngredientsCommand> ingredients = new HashSet<>();
	private Set<CategoryCommand> categories = new HashSet<>();

}
