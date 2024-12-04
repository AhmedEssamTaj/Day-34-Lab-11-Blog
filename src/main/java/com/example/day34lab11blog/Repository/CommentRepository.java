package com.example.day34lab11blog.Repository;

import com.example.day34lab11blog.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {

    Comment findCommentById(Integer id);

    // get all comments by post id
    List<Comment> findCommentByPostId(Integer postId);

}
