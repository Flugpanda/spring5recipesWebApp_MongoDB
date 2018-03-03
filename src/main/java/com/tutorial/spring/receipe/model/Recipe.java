package com.tutorial.spring.receipe.model;

import java.util.HashSet;
import java.util.Set;

import com.tutorial.spring.receipe.model.enums.EDifficulty;

import lombok.Getter;
import lombok.Setter;

/**
 * The model for the recipe object with all the jpa goodness
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
public class Recipe {

	private String id;
	private String description;
	private Integer prepTime;
	private Integer cooktime;
	private String servings;
	private String source;
	private String url;
	private Byte[] image;
	private String directions;
	private Notes note;
	private EDifficulty difficulty;
	private Set<Ingredient> ingredients = new HashSet<>();
	private Set<Category> categories = new HashSet<>();

	/**
	 * A convenience method to do the bidirectional mapping for the ingredients and
	 * the recipes
	 * 
	 * @param ingredient
	 *            the ingredient to ad
	 * @return the recipe
	 */
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}

	/**
	 * Add the note to the recipe and do the bidirectional mapping
	 * 
	 * @param note
	 *            the note to add
	 */
	public void setNotes(Notes note) {
		this.note = note;
		note.setRecipe(this);
	}
}
