package com.rakuten.training.dal;

import java.util.List;

import com.rakuten.training.domain.User;

public interface userDAO {
	public User save(User toBeSaved);
	public List<User> findAllUsers();
	public User findById(int id);
	public boolean emailExists(String email);
}
