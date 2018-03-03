package com.tutorial.spring.receipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A JavaBean which will hold all the data for UnitOfMeasure object.
 *
 * @author Bastian Bräunel
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
	private String id;
	private String unitDescription;
}
