package com.thefirstlineofcode.crystal.examples.plugins.initial.test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RestController
@RequestMapping("/initial-test-data")
public class InitialTestDataController {
	@Autowired
	private InitialTestDataService initialTestDataService;
	
	@GetMapping("/total-users")
	public ModelAndView getTotalUsers() {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
		modelAndView.addObject("total-users", initialTestDataService.getTotalUsers());
		
		return modelAndView;
	}
}
