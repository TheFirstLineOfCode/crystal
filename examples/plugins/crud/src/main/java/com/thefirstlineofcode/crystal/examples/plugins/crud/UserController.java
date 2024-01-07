package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.User;
import com.thefirstlineofcode.crystal.framework.crud.BasicCrudController;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;
import com.thefirstlineofcode.crystal.framework.ui.BootMenu;
import com.thefirstlineofcode.crystal.framework.ui.reactadmin.Resource;

@RestController
@RequestMapping("/users")
@Resource(name = "users", recordRepresentation = "name", listViewName = "UserListView", menu = @BootMenu(label = "ca.title.users", priority = BootMenu.PRIORITY_MEDIUM + 500))
public class UserController extends BasicCrudController<Long, User> {
	@Autowired
	private UserService userService;
	
	@Override
	public IBasicCrudService<Long, User> getService() {
		return userService;
	}
	
	@Override
	protected boolean isDeleteResourceEnabled() {
		return false;
	}
	
	@Override
	protected boolean isUpdateResourceEnabled() {
		return false;
	}
}
