package com.test.rest.webservice.bean.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.rest.webservice.bean.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 5;
	static {
		users.add(new User(1, "A", new Date()));
		users.add(new User(2, "B", new Date()));
		users.add(new User(3, "C", new Date()));
		users.add(new User(4, "D", new Date()));
		users.add(new User(5, "E", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;

	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}

		}
		return null;
	}

	public User deleteById(int id) {
		Iterator<User> iterator=users.iterator();
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
