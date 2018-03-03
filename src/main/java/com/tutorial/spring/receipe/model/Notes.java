package com.tutorial.spring.receipe.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The model for the simple notes object This class uses project lombock.
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
public class Notes {

	private String id;
	private Recipe recipe;
	private String content;
}
