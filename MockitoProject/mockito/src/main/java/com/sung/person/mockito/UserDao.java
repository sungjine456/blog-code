package com.sung.person.mockito;

public class UserDao {
	public User findUser(String email){
		return new User("name", email);
	}
	
	public User findUser(){
		return new User("name", "email");
	}
}