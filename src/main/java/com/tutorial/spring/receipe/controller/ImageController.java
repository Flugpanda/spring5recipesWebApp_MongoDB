package com.tutorial.spring.receipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.tutorial.spring.receipe.commands.RecipeCommand;
import com.tutorial.spring.receipe.service.IImageService;
import com.tutorial.spring.receipe.service.IRecipeService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
@Controller
public class ImageController {

	private final IImageService imageService;
	private final IRecipeService recipeService;
	
	/**
	 * default constructor for dependency injection
	 * 
	 * @param imageService
	 * @param recipeService
	 */
	public ImageController(IImageService imageService, IRecipeService recipeService) {
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	/**
	 * open the view to upload a image
	 */
	@GetMapping("/recipes/{recipeId}/image/upload")
	public String uploadImage(@PathVariable String recipeId, Model model) {
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
		log.debug(this.getClass().toString() + ":uploadImage - Open the view to upload an image for the recipe [" + recipeId + "].");
		model.addAttribute("recipe", recipeCommand);
		
		return "/recipes/image/imageUploadForm";
	}
	
	/**
	 * save the image and redirect it to the overview
	 */
	@PostMapping
	@RequestMapping("/recipes/{recipeId}/image/save")
	public String saveImage(@PathVariable String recipeId, @ModelAttribute MultipartFile imagefile) {
		imageService.saveImageFile(Long.valueOf(recipeId), imagefile);
		
		return "redirect:/recipes/" + recipeId + "/show";
	}
}
