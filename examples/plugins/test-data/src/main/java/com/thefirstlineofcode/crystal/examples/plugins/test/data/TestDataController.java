package com.thefirstlineofcode.crystal.examples.plugins.test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.thefirstlineofcode.crystal.plugins.react.admin.RaCustomController;

@RestController
@RequestMapping("/test-data")
public class TestDataController extends RaCustomController<ModelAndView> {
	@Autowired
	private TestDataService testDataService;
	
	@Override
	protected ModelAndView processInitialRequest() {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
		modelAndView.addObject("total-users", testDataService.getTotalUsers());
		
		return modelAndView;
	}
}
