package com.tutorial.spring.receipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
 * A controller to handle the spring mvc interaction for saving and rendering an image
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
		
		return "recipes/image/imageUploadForm";
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
	
	/**
	 * load the image from the database and display it via Tomcat
	 */
    @GetMapping("recipes/{recipeId}/image")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));

        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
