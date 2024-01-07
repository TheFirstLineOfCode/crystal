package com.thefirstlineofcode.crystal.examples.plugins.crud;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.Post;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

public interface IPostService extends IBasicCrudService<Long, Post> {}
