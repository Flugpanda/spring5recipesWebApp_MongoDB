package com.tutorial.spring.receipe.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
public class Category {

	private String id;
	private String description;
	private Set<Recipe> recipes = new HashSet<>();
}
