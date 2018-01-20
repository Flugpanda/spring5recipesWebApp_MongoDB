package com.tutorial.spring.receipe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The model for the simple notes object This class uses project lombock.
 * 
 * @author Bastian Bräunel
 *
 */
@Data
@EqualsAndHashCode(exclude = { "recipe" })
@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Recipe recipe;

	@Lob // this will increase the size from hibernates standard 256 chars
	private String content;
}
