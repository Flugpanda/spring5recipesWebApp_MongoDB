package com.tutorial.spring.receipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorial.spring.receipe.repositories.IRecipseRepository;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Controller
public class RecipeController{

	private IRecipseRepository recipseRepository;

	public RecipeController(IRecipseRepository recipseRepository) {
		this.recipseRepository = recipseRepository;
	}

	@RequestMapping("/recipes")
	public String findAllRecipies(Model model) {
		
		model.addAttribute("allRecipes", recipseRepository.findAll());
		
		return "recipes";
	}

}
