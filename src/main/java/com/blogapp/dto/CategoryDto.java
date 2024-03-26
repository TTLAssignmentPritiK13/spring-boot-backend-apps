package com.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;

    @NotEmpty(message = "Category title must not be empty or blank")
    @Size(min = 4, message = "Category title must be min 4 characters")
    private String categoryTitle;

    @NotEmpty(message = "Category description must not be empty or blank")
    @Size(min = 10, max = 100, message = "Category description must be min 10 & max 100 characters")
    private String categoryDescription;
}
