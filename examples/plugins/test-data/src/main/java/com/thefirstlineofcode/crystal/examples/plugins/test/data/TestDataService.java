package com.thefirstlineofcode.crystal.examples.plugins.test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.UserRepository;

@Service
public class TestDataService implements ITestDataService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void loadTestData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanTestDat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getTotalUsers() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}
}
