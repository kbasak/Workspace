package com.exception.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exception.demo.dto.UserDTO;
import com.exception.demo.entity.Users;
import com.exception.demo.exceptionHandler.UserNotFoundException;
import com.exception.demo.service.UsersService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	public UsersService usersService;

	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody @Valid UserDTO userDTO) {

		Users users = new Users();

		users.setName(userDTO.getName());
		users.setAge(userDTO.getAge());
		users.setEmail(userDTO.getEmail());
		users.setPhone(userDTO.getPhone());
		users.setNationality(userDTO.getNationality());

		usersService.saveUsers(users);

		return new ResponseEntity<Object>(new Users(users.getUserId(), users.getName(), users.getAge(),
				users.getEmail(), users.getPhone(), users.getNationality()), HttpStatus.CREATED);

	}
	
	@GetMapping("/findUsers")
	public ResponseEntity<Object> findUsers(){
		List<Users> details=usersService.findUsers();
		return new ResponseEntity<Object>(details,HttpStatus.OK);
	}
	
	@GetMapping("/findUsers/{id}")
	public ResponseEntity<Object> findUserById(@PathVariable int id) throws UserNotFoundException{
		Optional<Users> details=usersService.findUserById(id);
		return new ResponseEntity<Object>(details,HttpStatus.OK);
	}

	//Update User
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id,@RequestBody @Valid UserDTO userDTO) throws UserNotFoundException{
		Optional<Users> details=usersService.findUserById(id);
		if(details.equals(Optional.empty())) {
			throw new UserNotFoundException("User Not Found with User Id: "+id);
		}
		else {
			Users users = new Users();

			users.setName(userDTO.getName());
			users.setAge(userDTO.getAge());
			users.setEmail(userDTO.getEmail());
			users.setPhone(userDTO.getPhone());
			users.setNationality(userDTO.getNationality());

			usersService.saveUsers(users);

			return new ResponseEntity<Object>(new Users(users.getUserId(), users.getName(), users.getAge(),
					users.getEmail(), users.getPhone(), users.getNationality()), HttpStatus.CREATED);
		}
	}

	//delete user
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) throws UserNotFoundException{
		Optional<Users> details=usersService.findUserById(id);
		if(details.equals(Optional.empty())) {
			throw new UserNotFoundException("User Not Found with User Id: "+id);
		}
		else {
			usersService.deleteUser(id);
			return new ResponseEntity<Object>("User Deleted Successfully",HttpStatus.OK);
		}
	}
}
