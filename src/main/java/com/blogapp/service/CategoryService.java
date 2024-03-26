package com.blogapp.service;

import com.blogapp.dto.CategoryDto;
import com.blogapp.dto.UserDto;
import com.blogapp.entities.Category;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = dtoToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryToDto(savedCategory);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", " Category Id ", id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(category);
        return categoryToDto(updatedCategory);
    }

    public CategoryDto getCategoryById(Integer id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", " Category Id ", id));
        return categoryToDto(category);
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List <CategoryDto> categoryDtos = categories.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    public void deleteCategory(Integer id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", " Category Id ", id));
        categoryRepository.delete(category);
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        // CategoryDto object converted into Category entity
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

    public CategoryDto categoryToDto(Category category){
        // Category entity converted into CategoryDto object
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }
}
