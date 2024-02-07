package com.masai.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.blog.entities.Category;
import com.masai.blog.entities.Post;
import com.masai.blog.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	
	public List<Post> findByTitleContaining(String key);

}
