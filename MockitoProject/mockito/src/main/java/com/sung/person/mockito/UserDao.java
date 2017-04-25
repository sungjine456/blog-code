package com.sung.person.mockito;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	public User findUser(String email){
		return new User("name", email);
	}
	
	public User findUser(){
		return new User("name", "email");
	}
}
