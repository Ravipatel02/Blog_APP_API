package com.masai.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.blog.entities.Category;
import com.masai.blog.exception.ResourceNotFoundException;
import com.masai.blog.payloads.CategoryDto;
import com.masai.blog.repository.CategoryRepository;
import com.masai.blog.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category category1 = this.categoryRepo.save(category);
		return this.modelMapper.map(category1, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		this.categoryRepo.save(category);
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","categoryId",id));
		this.categoryRepo.delete(category);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> category = this.categoryRepo.findAll();
		List<CategoryDto> categoryDto = category.stream().map(cate -> this.modelMapper.map(cate, CategoryDto.class)).collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","categoryid",id));
		return this.modelMapper.map(category, CategoryDto.class);
	}

}
