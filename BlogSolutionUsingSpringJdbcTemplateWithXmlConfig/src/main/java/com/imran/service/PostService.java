package com.imran.service;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.imran.common.BaseService;
import com.imran.model.Post;



public class PostService extends BaseService{
		
	@Autowired 
	JdbcTemplate jt; 
	  
//	public void setTemplate(JdbcTemplate template) {  
//	    this.template = template;  
//	}  
	
//	 @Autowired  
//	 DataSource dataSource;  
//	 private  Statement stmt;

	
	
	public List<Post> postList() {
		System.out.println("This is Post list");
		String getAllDataQuery = "SELECT * FROM `post` ORDER BY ID"; //  LIMIT " + 1 + ","+ 2 
				List<Post> postList  = getJdbcTemplate().query(getAllDataQuery,
						new BeanPropertyRowMapper(Post.class));
		return postList;
	}
	
	public int createPost(String title, String body, String image) {
		int createStatus = 0;	
	    String sql="insert into post(`title`, `body`, `feature_image`) values('" + title + "','"+ body + "', '" + image + "')";  
	    createStatus = jt.update(sql);
		return createStatus;

	}
	
	public int updatePost(String title, String body, String image, long id) {
		int updateStatus = 0;
			String updateQuery = "UPDATE post SET title='"+title+"', body='"+body+"', feature_image = '"+image+"' WHERE id ='"+id+"'";
			updateStatus = jt.update(updateQuery);
		return updateStatus;
	}
	
	
	public Post findByPostId(Long postId){
		String sql = "SELECT * FROM post WHERE id = ?";
		Post postObj = (Post)getJdbcTemplate().queryForObject(
				sql, new Object[] { postId },
				new BeanPropertyRowMapper(Post.class));
		return postObj;
	}
		
	public int deletePost(long id){
		Post post = findByPostId(id);
		if(!post.getFeature_image().equals("")){
			removeFile(post.getFeature_image());
		}
		String deleteQuery ="DELETE FROM post WHERE `id`='"+id+"'";		
		int status = getJdbcTemplate().update(deleteQuery);
		return status;
	}

}