package com.tutorial.spring.receipe.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(exclude = { "recipe" })
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;

	// FetchType.EAGER will tell hibernate to load the object every time from the
	// database
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeas;

	@ManyToOne
	private Recipe recipe;
}
