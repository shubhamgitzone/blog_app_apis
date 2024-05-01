package com.shubham.blog.controllers;

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

import com.shubham.blog.payloads.ApiResponse;
import com.shubham.blog.payloads.CategoryDTO;
import com.shubham.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto) {
		CategoryDTO createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDTO>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto, @PathVariable Integer id) {
		CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully", true),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		List<CategoryDTO> categories = this.categoryService.getCategories();
		return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getOneCategory(@PathVariable Integer id) {
		CategoryDTO categoryDTO = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}

}
