package com.masai.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.masai.blog.payloads.ApiResponce;
import com.masai.blog.payloads.UserDto;
import com.masai.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	//post mapping
	@PostMapping("/")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto){
		UserDto createUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	}
	
	//put mapping 
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable Integer id){
		UserDto updateUser = userService.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	
	//get mapping
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		UserDto user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	//get mapping all data
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	// delete mapping
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("user deleted",true),HttpStatus.OK);
	}
	
	

}
