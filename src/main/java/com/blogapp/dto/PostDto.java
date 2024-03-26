package com.blogapp.dto;

import com.blogapp.entities.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int postId;

    @NotEmpty(message = "Post Title must not be empty or blank")
    @Size(min = 10, max = 100, message = "Comment must be min 10 & max 100 characters")
    private String postTitle;

    @NotEmpty(message = "Post Comment must not be empty or blank")
    @Size(min = 10, max = 1000, message = "Post comment must be min 10 & max 1000 characters")
    private String postContent;

    private String imageName;

    private Date postedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();

}
