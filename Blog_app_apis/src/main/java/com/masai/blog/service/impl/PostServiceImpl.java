package com.masai.blog.service.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.blog.entities.Category;
import com.masai.blog.entities.Post;
import com.masai.blog.entities.User;
import com.masai.blog.exception.ResourceNotFoundException;
import com.masai.blog.payloads.PageResponce;
import com.masai.blog.payloads.PostDto;
import com.masai.blog.repository.CategoryRepository;
import com.masai.blog.repository.PostRepository;
import com.masai.blog.repository.UserRepository;
import com.masai.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto ,  Integer userId, Integer categoryId ) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user_id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category_id",categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostImage("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post saveData = this.postRepo.save(post);
		return this.modelMapper.map(saveData, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Post_id",id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setPostImage(postDto.getPostImage());
		Post save = this.postRepo.save(post);
		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","post_id",id));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PageResponce getAllPost(Integer pageNumber, Integer pageSize , String sortBy ,String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("dase")) {
			sort = Sort.by(sortBy).descending();
		}else {
			sort = Sort.by(sortBy).ascending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = this.postRepo.findAll(p);
		List<Post> content = page.getContent();
		List<PostDto> collect = content.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PageResponce pageResponce = new PageResponce();
		pageResponce.setContent(collect);
		pageResponce.setPageNumber(page.getNumber());
		pageResponce.setPageSize(page.getSize());
		pageResponce.setTotalElement(page.getTotalElements());
		pageResponce.setTotalPage(page.getTotalPages());
		return pageResponce;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user Id",id));
		List<Post> userData = this.postRepo.findByUser(user);
		List<PostDto> collect = userData.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer id) {
		Category catgory = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Categeory","catgory_id",id));
		List<Post> categoryData = this.postRepo.findByCategory(catgory);

		List<PostDto> collect = categoryData.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deletePost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","postId",id));
		this.postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> getDataBysearch(String key) {
		List<Post> post = this.postRepo.findByTitleContaining(key);
		List<PostDto> collect = post.stream().map(p -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return collect;
	}
	


}
