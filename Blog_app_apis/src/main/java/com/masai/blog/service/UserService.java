package com.masai.blog.service;

import java.util.List;

import com.masai.blog.payloads.UserDto;

public interface UserService {
	//create user
	public UserDto createUser(UserDto user);
	// update user
	public UserDto updateUser(UserDto user , Integer id);
	// delete user
	public void deleteUser(Integer id);
	//get userById
	public UserDto getUserById(Integer id);
	//get allUser
	public List<UserDto> getAllUser();

}
