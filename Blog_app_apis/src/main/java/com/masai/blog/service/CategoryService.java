package com.masai.blog.service;

import java.util.List;

import com.masai.blog.payloads.CategoryDto;

public interface CategoryService {
	// create category
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	// update category
	public CategoryDto updateCategory(CategoryDto categoryDto , Integer id);
	
	// delete category
	public void deleteCategory(Integer id);
	
	// get all category
	public List<CategoryDto> getAllCategory();
	
	// get category
	public CategoryDto getCategory(Integer id);

}
