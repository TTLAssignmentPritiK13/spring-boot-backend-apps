package com.blogapp.service;

import com.blogapp.dto.CommentDto;
import com.blogapp.entities.Comment;
import com.blogapp.entities.Post;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CommentDto createComment(CommentDto commentDto, Integer postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", " Post Id ", postId));

        Comment comment = dtoToComment(commentDto);
        comment.setPost(post);
        comment.setComment(comment.getComment());
        comment.setCommentedDate(new Date());

        Comment savedComment = commentRepository.save(comment);
        return commentToDto(savedComment);
    }

    public void deleteComment(Integer commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", " Comment Id ", commentId));
        commentRepository.delete(comment);
    }

    public Comment dtoToComment(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    public CommentDto commentToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }
}
