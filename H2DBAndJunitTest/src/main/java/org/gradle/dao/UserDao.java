package org.gradle.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.gradle.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession session;
	
	public UserDao(SqlSession session){
		this.session = session;
	}
	
	public UserVo findUserById(long id){
		return session.selectOne("findUserById", id);
	}
	
	public List<UserVo> findAllUsers(){
		return session.selectList("findAllUsers");
	}
	
	public void insertUser(UserVo user){
		session.insert("insertUser", user);
	}
	
	public void updateUser(UserVo user){
		session.update("updateUser", user);
	}
}
