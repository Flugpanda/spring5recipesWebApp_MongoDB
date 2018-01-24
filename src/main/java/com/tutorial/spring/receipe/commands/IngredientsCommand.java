package com.tutorial.spring.receipe.commands;

import java.math.BigDecimal;

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
public class IngredientsCommand {
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeas;
	private RecipeCommand recipe;
}
