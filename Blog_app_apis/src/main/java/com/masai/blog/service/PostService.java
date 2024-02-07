package com.masai.blog.service;

import java.util.List;

import com.masai.blog.payloads.PageResponce;
import com.masai.blog.payloads.PostDto;

public interface PostService {
	//create post
	PostDto createPost(PostDto postDto , Integer categoryId , Integer userId);
	
	// update post
	PostDto updatePost(PostDto postDto , Integer id);
	
	//get post by id
	PostDto getPostById(Integer id);
	
	// get all post
	PageResponce getAllPost(Integer pageNumber, Integer pageSize , String sortBy , String sortDir);
	
	// get post by user
	List<PostDto> getAllPostByUser(Integer id);
	
	// get post by category
	List<PostDto> getAllPostByCategory(Integer id);
	
	// delete post
	public void deletePost(Integer id);
	
	// search by key containing 
	List<PostDto> getDataBysearch(String key);

}
