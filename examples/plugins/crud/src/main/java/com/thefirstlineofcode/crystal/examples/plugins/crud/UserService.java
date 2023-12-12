package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.thefirstlineofcode.crystal.framework.crud.jpa.BasicCrudService;

@Service
public class UserService extends BasicCrudService<User, Long> implements IUsersService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected PagingAndSortingRepository<User, Long> getRepository() {
		return userRepository;
	}
}
