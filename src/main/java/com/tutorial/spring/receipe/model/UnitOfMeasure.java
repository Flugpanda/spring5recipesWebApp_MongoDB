package com.tutorial.spring.receipe.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The unit of measurement for the ingredients 
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
public class UnitOfMeasure {
	
	private String id;
	private String unitDescription;
}
