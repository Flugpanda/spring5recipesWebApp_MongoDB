package com.tutorial.spring.receipe.commands;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A JavaBean which will hold all the data for a Ingredients object from the forms in
 * the views.
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientsCommand {
	private String id;
	private String recipeId;

	@NotBlank
	@Size(min = 3, max = 255)
	private String description;
	
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeas;
	private RecipeCommand recipe;
}
