package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.User;
import com.thefirstlineofcode.crystal.framework.crud.BasicCrudController;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

@RestController
@RequestMapping("/users")
public class UsersController extends BasicCrudController<User> {
	@Autowired
	private UserService userService;
	
	@Override
	public IBasicCrudService<User> getService() {
		return userService;
	}
}
