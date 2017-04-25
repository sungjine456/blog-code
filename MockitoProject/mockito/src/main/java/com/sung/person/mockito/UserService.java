package com.sung.person.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired private UserDao userDao;
	
	public User findUser(){
		return userDao.findUser();
	}
	
	public User findUser(String email){
		return userDao.findUser(email);
	}
}
