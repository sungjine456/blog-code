package com.sung.person.mockito;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private SqlSession session;
	
	public UserDao(){}
	public UserDao(SqlSession session){
		this.session = session;
	}
	
	public User findUser(String email){
		return session.selectOne("findUserByEmail", email);
	}
	
	public User findUser(){
		return session.selectOne("findUser");
	}
}
