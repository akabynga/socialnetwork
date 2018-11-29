package com.socialnetwork.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.entity.User;
import com.socialnetwork.service.UserService;

@RestController(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public @ResponseBody Long create(@RequestBody User user) {
		return userService.create(user);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public @ResponseBody List<User> fetchAll(@RequestParam int amount, @RequestParam int skip) {
		return userService.getAll(amount, skip);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public @ResponseBody User fetchById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}")
	public @ResponseBody Long update(@RequestBody @Valid User user) {
		return userService.update(user);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/{id}")
	public @ResponseBody void delete(@PathVariable("id") Long id) {
		userService.remove(id);
	}

}
