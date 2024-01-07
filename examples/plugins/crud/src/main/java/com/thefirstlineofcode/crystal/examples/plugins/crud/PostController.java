package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.Post;
import com.thefirstlineofcode.crystal.framework.crud.BasicCrudController;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;
import com.thefirstlineofcode.crystal.framework.error.ValidationException;
import com.thefirstlineofcode.crystal.framework.ui.BootMenu;
import com.thefirstlineofcode.crystal.framework.ui.reactadmin.Resource;

@RestController
@RequestMapping("/posts")
@Resource(name = "posts",
	listViewName = "PostListView",
	showViewName = "PostShowView",
	editViewName =  "PostEditView",
	menu = @BootMenu(label = "ca.title.posts", priority = BootMenu.PRIORITY_MEDIUM + 400))
public class PostController extends BasicCrudController<Long, Post> {
	@Autowired
	private PostService postService;
	
	@Override
	protected void validUpdated(Post updated, Post existed) {
		if (updated.getTitle() == null || "".equals(updated.getTitle()))
			throw new ValidationException("Null post title.");
		
		if (updated.getBody() == null || "".equals(updated.getBody()))
			throw new ValidationException("Null post body.");
	}
	
	@Override
	public IBasicCrudService<Long, Post> getService() {
		return postService;
	}
}
