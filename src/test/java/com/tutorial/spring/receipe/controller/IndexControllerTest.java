package com.tutorial.spring.receipe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class IndexControllerTest {

	private IndexController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new IndexController();
	}

	/**
	 * Test Spring MVC Controller with the MockMvc from Spring framework
	 */
	@Test
	public void springMvcTest() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(get("/")).
		andExpect(status().isOk()).
		andExpect(view().name("index"));
	}
}
