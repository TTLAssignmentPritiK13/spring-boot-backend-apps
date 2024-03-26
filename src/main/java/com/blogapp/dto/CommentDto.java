package com.blogapp.dto;

import com.blogapp.entities.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDto {

    private int commentId;
    private String comment;
    private Date commentedDate;
}
