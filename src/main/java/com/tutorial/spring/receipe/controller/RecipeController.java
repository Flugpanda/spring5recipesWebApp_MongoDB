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

	/**
	 * List all recipes from the database
	 */
	@RequestMapping("/recipes")
	public String findAllRecipies(Model model) {
		log.debug(this.getClass().toString() + ": loading recipes webpage.");
		model.addAttribute("allRecipes", recipeService.getRecipes());
		
		return "recipes";
	}

	/**
	 * Display the recipe with the given id from the url in a new web site,
	 * using REST style url formatting 
	 */
	@RequestMapping("/recipes/{id}/show")
	public String showById(Model model, @PathVariable String id) {
		log.debug(this.getClass().toString() + ":showById - Loading the webpage recipes/show.html.");
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		
		return "recipes/show";
	}
	
	/**
	 * Open a web site to create a new recipe
	 */
	@RequestMapping("/recipes/new")
	public String createNewRecipe(Model model) {
		log.debug(this.getClass().toString() + ":createNewRecipe - Loading the webpage /recipes/addRecipe.html");
		model.addAttribute("recipe", new RecipeCommand());

		return "recipes/createUpdateRecipes";
	}
	
	/**
	 * This method will open a web site to manipulate a recipe,
	 * using REST style url formatting.
	 */
	@RequestMapping("/recipes/{id}/update")
	public String updateRecipe(Model model, @PathVariable String id) {
		log.debug(this.getClass().toString() + ":updateRecipe - Loading the webpage /recipes/createUpdateRecipes.html");
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		
		return "recipes/createUpdateRecipes";
	}
	
	/**
	 * Save a new recipe and open the web site to show the newly created recipe
	 * 
	 * 
	 * @ModelAttribute tells Spring to bind the inputs to an object from the type {@link RecipeCommand}
	 * 
	 * @RequestMapping(name = "recipe", method = RequestMethod.POST)
	 * 		old way of telling Spring MVC what http method will be used
	 */
	//@RequestMapping(name = "recipe", method = RequestMethod.POST)
	@PostMapping
	@RequestMapping("recipeToSave")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand saveCommand = recipeService.saveRecipeCommand(command);
		log.debug(this.getClass().toString() + ":saveOrUpdate - Loading the webpage /recipes/show/" + command.getId() + ".html");
		
		// redirect: 	tells Spring MVC to redirect to a specific url
		// appending the id of the newly created recipe and open the recipe show web site of this recipe
		return "redirect:/recipes/" + saveCommand.getId() + "/show";
	}
}
