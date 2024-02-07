package com.masai.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.blog.entities.User;
import com.masai.blog.exception.ResourceNotFoundException;
import com.masai.blog.payloads.UserDto;
import com.masai.blog.repository.UserRepository;
import com.masai.blog.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User user1 = this.repo.save(user);
		UserDto user2 = this.userToDto(user1);
		return user2;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user_id",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User save = this.repo.save(user);
		return this.userToDto(save);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		this.repo.delete(user);
		
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> user = this.repo.findAll();
		List<UserDto> allUser = user.stream().map(User -> this.userToDto(User)).collect(Collectors.toList());
		return allUser;
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto , User.class);
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setPassword(userDto.getPassword());
		 * user.setAbout(userDto.getAbout());
		 */
		
		return user;
		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}
	

}
