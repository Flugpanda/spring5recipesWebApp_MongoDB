package com.tutorial.spring.receipe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.tutorial.spring.receipe.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * A controller to handle custom exception to show custom error pages.
 * 
 * @author Bastian Bräunel
 *
 */
@Slf4j
@ControllerAdvice
public class ErrorController {

	/**
	 * Show a custom 404 site
	 * 
	 * For the purpose of just returning a error code, you can simply use the annotation:
	 * 
	 * @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Element not found")
	 */
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(HttpServletRequest req, Exception ex) {
		log.error(this.getClass().toString() + ":handleNotFound - The element was not found.");
		
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("reason", "Element not found");
	    mav.addObject("exception", ex);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("errors/404error");
	    mav.setStatus(HttpStatus.NOT_FOUND);
	    
	    return mav;		
	}
}
