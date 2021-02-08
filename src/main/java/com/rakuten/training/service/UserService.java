package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.User;

public interface UserService {
	public User registerUser(User newUser);

	public List<User> findAllUsers();

	public User findUserById(int id);
	public boolean passwordValidation(String password);
}
