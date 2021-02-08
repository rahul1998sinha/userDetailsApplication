package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.User;

@Repository
@Transactional
public class UserDaoJPAImpl implements userDAO {

	@Autowired
	EntityManager em;

	@Override
	public User save(User toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public List<User> findAllUsers() {
		Query q = em.createQuery("select u from User as u");
		return q.getResultList();
	}

	@Override
	public User findById(int id) {
		User aUser = em.find(User.class, id);
		return aUser;
	}

	@Override
	public boolean emailExists(String email) {
		Query q = em.createQuery("select u from User u where u.userEmail=:emailId");
		q.setParameter("emailId", email);
		List<Query> result = q.getResultList();
		if(result.size()==0)
			return false;
		return true;
	}

}
