package com.tutorial.spring.receipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  A JavaBean which will hold all the data for Category object.
 *  
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
	
	private String id;
	private String description;
}
