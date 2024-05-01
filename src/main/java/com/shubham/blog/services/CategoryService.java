package com.shubham.blog.services;

import java.util.List;

import com.shubham.blog.payloads.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDto);
	
	CategoryDTO updateCategory(CategoryDTO categoryDto,Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDTO getCategory(Integer categoryId);
	
	List<CategoryDTO> getCategories();

}
