package com.tutorial.spring.receipe.commands;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.tutorial.spring.receipe.model.enums.EDifficulty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  A JavaBean which will hold all the data for a Recipe object from the forms in the views.
 *  The data will be validated by using javax.validation.constraints and hibernate.validator.constraints
 *  
 *  @see https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String description;
	
	@Min(1)
	@Max(999)
	private String prepTime;
	
	@Min(1)
	@Max(999)
	private String cookTime;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String servings;	
	private String source;
	
	@URL
	private String url;
	
	@NotBlank
	private String directions;	
	private Byte[] image;
	private EDifficulty difficulty;
	private NotesCommand note;	
	private Set<IngredientsCommand> ingredients = new HashSet<>();
	private Set<CategoryCommand> categories = new HashSet<>();

}
