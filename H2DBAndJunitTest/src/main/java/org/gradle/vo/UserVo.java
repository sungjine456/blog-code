package org.gradle.vo;

import org.apache.ibatis.type.Alias;

@Alias("userVo")
public class UserVo {
	private long id;
    private String name;
    private String email;
    private String password;
    
    public UserVo(){}
    public UserVo(long id, String name, String email, String password){
    	this.id = id;
    	this.name = name;
    	this.email = email;
    	this.password = password;
    }
    public UserVo(String name, String email, String password){
    	this.name = name;
    	this.email = email;
    	this.password = password;
    }

    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserVo(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "id : " + id + ", name : " + name + ", email : " + email + ", password : " + password;
	}
}
