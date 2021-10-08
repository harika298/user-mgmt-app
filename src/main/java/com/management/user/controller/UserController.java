package com.management.user.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.user.entities.Role;
import com.management.user.entities.User;
import com.management.user.exceptions.InvalidInputException;
import com.management.user.models.ResponseModel;
import com.management.user.models.UserModel;
import com.management.user.service.UserService;
import com.management.user.util.Utility;

@RestController
@RequestMapping(value = "/user-service")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(value="/users", consumes= {MediaType.APPLICATION_JSON_VALUE})
	private ResponseEntity<ResponseModel> insertUser(@Valid @RequestBody UserModel userModel) {
		if(userModel==null || Utility.isNullOrEmpty(userModel.getUserName())) {
			throw new InvalidInputException("Invalid inputs..");
		}
		User user = modelMapper.map(userModel, User.class);
		userService.insertOrUpdateUser(user);
		return userService.setResponse("User Successfully inserted",HttpStatus.OK);
	}
	
	
	@PutMapping(value="/users", consumes= {MediaType.APPLICATION_JSON_VALUE})
	private ResponseEntity<ResponseModel> updateUser(@RequestBody UserModel userModel) {
		User user = modelMapper.map(userModel, User.class);
		userService.updateUser(user);
		return userService.setResponse("User Successfully updated",HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	private ResponseEntity<ResponseModel> deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		return userService.setResponse("User Successfully deleted",HttpStatus.OK);
	}
	
	@DeleteMapping("/users")	
	private void deleteUserRoleById(@RequestBody User user) {
		userService.deleteUserRoleById(user);
	}
	
	@GetMapping(path = "/users/{id}", produces="application/json")
	private Optional<User> getUserById(@PathVariable int id) {
		
	    return userService.getUserById(id);
	}
	
	@GetMapping(path = "/getUserRolesById/{id}", produces="application/json")
	private Set<Role> getUserRolesById(@PathVariable int id) {
		return userService.getUserRolesById(id);
	}
	
	@GetMapping(path = "/users", produces="application/json")
	private List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
	
}
