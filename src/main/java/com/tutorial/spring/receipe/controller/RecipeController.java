package com.tutorial.spring.receipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorial.spring.receipe.service.IRecipeService;

/**
 * A simple spring mvc controller to serve the recipes.html page
 * and provide the necessary data through the service
 * 
 * @author Bastian Bräunel
 *
 */
@Controller
public class RecipeController{

	private IRecipeService recipeService;
	
	/**
	 * Constructor based injection of the service
	 * 
	 * @param recipeService		the service injected by spring
	 */
	public RecipeController(IRecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipes")
	public String findAllRecipies(Model model) {
		
		model.addAttribute("allRecipes", recipeService.getRecipes());
		
		return "recipes";
	}

}
