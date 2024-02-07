package com.masai.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.blog.payloads.ApiResponce;
import com.masai.blog.payloads.CategoryDto;
import com.masai.blog.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/categories")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl category;
	
	//post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto catgory){
		CategoryDto category1 = this.category.createCategory(catgory);
		return new ResponseEntity<CategoryDto>(category1,HttpStatus.CREATED);
	}
	
	//put
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category ,@PathVariable Integer id ){
		CategoryDto category1 = this.category.updateCategory(category, id);
		return ResponseEntity.ok(category1);
	}
	
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
		CategoryDto category2 = this.category.getCategory(id);
		return ResponseEntity.ok(category2);
	}
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = this.category.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategory , HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer id){
		this.category.deleteCategory(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("deleted successfuly",true),HttpStatus.OK );
	}

}
