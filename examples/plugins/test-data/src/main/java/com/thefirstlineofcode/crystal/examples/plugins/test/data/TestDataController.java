package com.thefirstlineofcode.crystal.examples.plugins.test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.thefirstlineofcode.crystal.framework.ui.CustomView;
import com.thefirstlineofcode.crystal.framework.ui.ViewMenu;
import com.thefirstlineofcode.crystal.plugins.react.admin.RaCustomController;

@RestController
@RequestMapping("/test-data")
@CustomView(name = "test-data", viewName = "TestDataView", menu = @ViewMenu(parent = "tools", label = "ca.menu.testData"))
public class TestDataController extends RaCustomController<ModelAndView> {
	@Autowired
	private TestDataService testDataService;
	
	@Override
	protected ModelAndView processInitialRequest() {
		return getTotalUsers();
	}
	
	@GetMapping("/total-users")
	public ModelAndView getTotalUsers() {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
		modelAndView.addObject("total_users", testDataService.getTotalUsers());
		
		return modelAndView;
	}
}
