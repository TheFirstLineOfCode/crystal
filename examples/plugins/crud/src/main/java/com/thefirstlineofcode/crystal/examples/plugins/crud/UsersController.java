package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.framework.crud.BasicCrudController;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

@RestController
@RequestMapping("users")
public class UsersController extends BasicCrudController<User> {
	@Override
	public IBasicCrudService<User> getService() {
		// TODO Auto-generated method stub
		return null;
	}
}
