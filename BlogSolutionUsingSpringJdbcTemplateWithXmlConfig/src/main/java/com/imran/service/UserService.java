package com.imran.service;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.imran.common.BaseService;
import com.imran.model.Post;
import com.imran.model.User;



public class UserService extends BaseService{
	
	@Autowired 
	JdbcTemplate jt;  
	
	
	public int userRegistration(String name, String email, String password, String gender) {
		int resultStatus = 0;	
		String sql="insert into user (`active_status`, `email`, `gender`, `name`, `password`) values(1,'"+email+"','"+gender+"','"+name+"','"+password+"')";  
		resultStatus = jt.update(sql);
	    return resultStatus;
	}
	

	public User userLogin(String email, String password){
		User logUser = null;
		List<User> userList = new ArrayList<User>();
		String getUserQuery ="select * from user WHERE email='"+email+"'";
		User singleUser = userByEmailId(email);
		   if(singleUser.getPassword().equals(password)){
			   logUser = singleUser;  
		   } 
		return logUser;	
	}
	
	public User userByEmailId(String email){
		String sql = "SELECT * FROM user WHERE email = ?";
		User userObj = (User)getJdbcTemplate().queryForObject(sql, new Object[] { email },new BeanPropertyRowMapper(User.class));
		return userObj;
	}

}