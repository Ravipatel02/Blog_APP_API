package com.masai.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.masai.blog.payloads.ApiResponce;
import com.masai.blog.payloads.PageResponce;
import com.masai.blog.payloads.PostDto;
import com.masai.blog.service.impl.ImageUpload;
import com.masai.blog.service.impl.PostServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/post")
public class PostCantroller {
	@Autowired
	private PostServiceImpl service;
	
	@Autowired
	private ImageUpload upload;
	
	@Value("${project.image}")
	private String path;
	// post mapping
	@PostMapping("/user/{u_id}/category/{c_id}")
	public ResponseEntity<PostDto> AddPost(@RequestBody PostDto postDto , @PathVariable Integer u_id,
			@PathVariable Integer c_id){
		PostDto post = this.service.createPost(postDto, u_id, c_id);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
		
	}
	// update post
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer id){
		PostDto updatePost = this.service.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id){
		PostDto post = this.service.getPostById(id);
		return ResponseEntity.ok(post);
		
	}
	// delete by id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce> deletePost(@PathVariable Integer id){
		this.service.deletePost(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("deleted successfully !!",true),HttpStatus.OK);
	}
	// get All post 
	@GetMapping("/")
	public ResponseEntity<PageResponce> getAllPost(
			@RequestParam(value = "pageNumber" , defaultValue = "0", required=false) Integer pageNumber
			,@RequestParam(value="pageSize" , defaultValue = "5",required=false) Integer pageSize,
			@RequestParam(value ="sortBy" , defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "byDir",defaultValue = "ase" , required = false) String byDir){
		PageResponce allPost = this.service.getAllPost(pageNumber , pageSize ,sortBy ,byDir);
		return new ResponseEntity<PageResponce>(allPost,HttpStatus.OK);
	}
	//get all post by user
	@GetMapping("/user/{id}")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer id){
		List<PostDto> allPostByUser = this.service.getAllPostByUser(id);
		return new ResponseEntity<List<PostDto>>(allPostByUser, HttpStatus.OK);
	}
	
	// get all post by category
	@GetMapping("/category/{id}")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer id){
		List<PostDto> allPostByCategory = this.service.getAllPostByCategory(id);
		return new ResponseEntity<List<PostDto>>(allPostByCategory , HttpStatus.OK);
	}
	// get data search by key
	@GetMapping("search/{key}")
	public ResponseEntity<List<PostDto>> getDataBySerchByKey(@PathVariable String key){
		List<PostDto> dataBysearch = this.service.getDataBysearch(key);
		return new ResponseEntity<List<PostDto>>(dataBysearch , HttpStatus.OK);
	}
	@PostMapping("/image/{id}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable Integer id,
			@RequestParam("image") MultipartFile image) throws IOException{
		PostDto post = this.service.getPostById(id);
		String name = this.upload.uploadImage(path, image);
		post.setPostImage(name);
		PostDto updatePost = this.service.updatePost(post, id);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);	
		
	}
	
	@GetMapping("image/{fileName}")
	public void getImage(@PathVariable String fileName ,HttpServletResponse responce) throws IOException {
		responce.setContentType(MediaType.IMAGE_JPEG_VALUE);
		InputStream resource = this.upload.getImage(path, fileName);
		StreamUtils.copy(resource, responce.getOutputStream());
	}

}
