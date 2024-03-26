package com.blogapp.repository;

import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    public List<Post> findByCategory(Category categoryID);

    public List<Post> findByUser(User user);

    public List<Post> searchByPostTitleContaining(String postTitle);
}
