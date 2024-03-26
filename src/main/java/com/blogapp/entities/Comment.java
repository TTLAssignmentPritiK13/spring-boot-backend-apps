package com.blogapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int commentId;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_Id")
    private Post post;

    private Date commentedDate;

}
