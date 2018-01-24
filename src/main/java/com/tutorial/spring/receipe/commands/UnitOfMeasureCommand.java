package com.tutorial.spring.receipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
	private Long id;
	private String unitDescription;
}
