package com.tutorial.spring.receipe.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * The ingredients that are used in a recipe This class uses project lombock.
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
public class Ingredient {

	private String id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasure unitOfMeas;
	private Recipe recipe;
}
