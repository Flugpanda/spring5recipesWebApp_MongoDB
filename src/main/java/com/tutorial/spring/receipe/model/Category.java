package com.tutorial.spring.receipe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Data
@EqualsAndHashCode(exclude = { "recipes" })
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	/**
	 * The name of the property of the other side of the many to many relationship
	 * defined at the Recipe class
	 */
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes = new HashSet<>();
}
