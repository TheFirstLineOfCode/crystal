package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.Post;
import com.thefirstlineofcode.crystal.framework.crud.BasicCrudController;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;
import com.thefirstlineofcode.crystal.framework.ui.BootMenu;
import com.thefirstlineofcode.crystal.framework.ui.reactadmin.Resource;

@RestController
@RequestMapping("/posts")
@Resource(name = "posts", listViewName = "PostListView", showViewName = "PostShowView", menu = @BootMenu(label = "ca.title.posts", priority = BootMenu.PRIORITY_MEDIUM + 400))
public class PostController extends BasicCrudController<Post, Long> {
	@Autowired
	private PostService postService;
	
	@Override
	public IBasicCrudService<Post, Long> getService() {
		return postService;
	}
}
