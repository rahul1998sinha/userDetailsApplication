package com.rakuten.training.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.userDAO;
import com.rakuten.training.domain.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	userDAO dao;

	@Override
	public User registerUser(User newUser) {
		if (!passwordValidation(newUser.getUserPassword()))
			throw new IllegalArgumentException(" Password is weak");
		else if (dao.emailExists(newUser.getUserEmail()))
			throw new IllegalArgumentException(" Email Exists");
		else {
			dao.save(newUser);
			return newUser;
		}
	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public User findUserById(int id) {
		User newUser = dao.findById(id);
		return newUser;
	}

	@Override
	public boolean passwordValidation(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);

		if (password == null) {
			return false;
		}
		Matcher m = p.matcher(password);
		return m.matches();
	}

}
