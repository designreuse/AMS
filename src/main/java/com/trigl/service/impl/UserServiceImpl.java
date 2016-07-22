package com.trigl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trigl.dao.UserMapper;
import com.trigl.entity.User;
import com.trigl.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User login(User user) {
		return userMapper.login(user);
	}
	
}
