package com.tutorial.spring.receipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.service.IRecipeService;

import lombok.extern.slf4j.Slf4j;

/**
 * A simple spring mvc controller to serve the recipes.html page
 * and provide the necessary data through the service
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
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
		log.debug(this.getClass().toString() + ": loading recipes webpage.");
		model.addAttribute("allRecipes", recipeService.getRecipes());
		
		return "recipes";
	}

	@RequestMapping("/recipes/show/{id}")
	public String showById(Model model, @PathVariable String id) {
		log.debug(this.getClass().toString() + ":showById - Loading the webpage recipes/show.html.");
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		
		return "recipes/show";
	}
	
	@RequestMapping("/recipes/new")
	public String createNewRecipe(Model model) {
		log.debug(this.getClass().toString() + ":createNewRecipe - Loading the webpage /recipes/addRecipe.html");
		model.addAttribute("recipe", new RecipeCommand());

		return "recipes/createUpdateRecipes";
	}
	
	/**
	 * @ModelAttribute tells Spring to bind the inputs to an object from the type {@link RecipeCommand}
	 * 
	 * @RequestMapping(name = "recipe", method = RequestMethod.POST)
	 * 		old way of telling Spring MVC what http method will be used
	 */
	//@RequestMapping(name = "recipe", method = RequestMethod.POST)
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand saveCommand = recipeService.saveRecipeCommand(command);
		
		// redirect: 	tells Spring MVC to redirect to a specific url
		// appending the id of the newly created recipe and open the recipe show web site of this recipe
		return "redirect:/recipes/show/" + saveCommand.getId();
	}
}
