package com.thefirstlineofcode.crystal.examples.plugins.crud;

import com.thefirstlineofcode.crystal.examples.plugins.data.accessor.User;
import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

public interface IUserService extends IBasicCrudService<User, Long> {}
