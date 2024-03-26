package com.blogapp.service;

import com.blogapp.dto.PagingResponse;
import com.blogapp.dto.PostDto;
import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repository.CategoryRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " User Id" ,userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " Category Id ", categoryId));
        Post post = dtoToPost(postDto);
        post.setImageName("default.png");
        post.setPostedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepository.save(post);
        return postToDto(savedPost);
    }

    public PostDto updatePost(PostDto postDto, Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", " Post Id ", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postRepository.save(post);
        return postToDto(updatedPost);
    }

    public PostDto getPostById(Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", " Post Id ", postId));
        return postToDto(post);
    }

    public List<PostDto> getAllPost(int pageNumber, int pageSize){
        //Implemented Paging here(add in other controllers)
        PagingResponse pagingResponse = new PagingResponse();
        Page<Post> postPage= postRepository.findAll(pagingResponse.pagingOperation(pageNumber,pageSize));
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    // get all post by category
    public List<PostDto> getPostsByCategory(Integer categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " Category Id ", categoryId));
        List<Post> posts = postRepository.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    // get all posts by user
    public List<PostDto> getPostsByUser(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " User Id " ,userId));
        List<Post> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    // search post by keyword
    public List<PostDto> searchByPostTitleContaining(String postTitle){
        List<Post> posts = postRepository.searchByPostTitleContaining(postTitle);
        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }
    public void deletePost(Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", " Post Id ", postId));
        postRepository.delete(post);
    }

    public Post dtoToPost(PostDto postDto){
        // PostDto object converted into Post entity
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    public PostDto postToDto(Post post){
        // Post entity converted into PostDto object
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
