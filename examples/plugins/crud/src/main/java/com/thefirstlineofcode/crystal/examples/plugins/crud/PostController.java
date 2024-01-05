package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.Post;
import com.thefirstlineofcode.crystal.framework.crud.BasicCrudController;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;
import com.thefirstlineofcode.crystal.framework.ui.CrudView;
import com.thefirstlineofcode.crystal.framework.ui.ViewMenu;

@RestController
@RequestMapping("/posts")
@CrudView(name = "posts", menu = @ViewMenu(label = "ca.title.posts", priority = ViewMenu.PRIORITY_MEDIUM + 400))
public class PostController extends BasicCrudController<Post> {
	@Autowired
	private PostService postService;
	
	@Override
	public IBasicCrudService<Post> getService() {
		return postService;
	}
}
