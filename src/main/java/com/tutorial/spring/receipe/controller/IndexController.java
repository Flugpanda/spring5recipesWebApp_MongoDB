package com.tutorial.spring.receipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A simple spring mvc controller to serve the index.html page
 * 
 * @author Bastian Bräunel
 *
 */
@Controller
public class IndexController {
	
	private final static String INDEX = "index";

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		return INDEX;
	}
}
