package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.User;
import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.UserRepository;
import com.thefirstlineofcode.crystal.framework.crud.jpa.BasicCrudService;

@Service
public class UserService2 extends BasicCrudService<User, Long> implements IUserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected PagingAndSortingRepository<User, Long> getRepository() {
		return userRepository;
	}
}