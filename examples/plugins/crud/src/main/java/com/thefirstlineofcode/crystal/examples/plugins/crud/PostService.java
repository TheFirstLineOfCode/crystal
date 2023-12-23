package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.Post;
import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.PostRepository;
import com.thefirstlineofcode.crystal.framework.crud.jpa.BasicCrudService;

@Service
public class PostService extends BasicCrudService<Post, Long> implements IPostService {
	@Autowired
	private PostRepository postRepository;
	
	@Override
	protected PagingAndSortingRepository<Post, Long> getRepository() {
		return postRepository;
	}
}
