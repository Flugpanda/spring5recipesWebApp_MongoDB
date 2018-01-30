package com.tutorial.spring.receipe.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tutorial.spring.receipe.model.Recipe;
import com.tutorial.spring.receipe.repositories.IRecipseRepository;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Service
public class ImageService implements IImageService {

	private final IRecipseRepository recipseRepository;
	
	/**
	 * default constructor for dependency injection	
	 * 
	 * @param recipseRepository
	 */
	public ImageService(IRecipseRepository recipseRepository) {
		this.recipseRepository = recipseRepository;
	}

	/**
	 * save an image to a recipe
	 */
	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		
		Byte[] choppedImage;
		Recipe recipe;
		Optional<Recipe> recipeOptional = recipseRepository.findById(Long.valueOf(recipeId));
		
		if (!recipeOptional.isPresent()) {
			throw new IllegalArgumentException("The recipe with the id [" + recipeId + "] was not found.");
		}else {
			recipe = recipeOptional.get();
		}
		
		if (file == nullValue()) {
			throw new IllegalArgumentException("The file mustn't be an empty object.");
		}
		
		try {
			// because Byte class is a wrapper for the primitive byte
			// we have to copy the bytes to store it at the recipe
			choppedImage = new Byte[file.getBytes().length];
			int i = 0;
			
			for (Byte b : file.getBytes()) {
				choppedImage[i++] = Byte.valueOf(b);
			}
			
			// save the image
			recipe.setImage(choppedImage);
			// persist the recipe
			recipseRepository.save(recipe);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
