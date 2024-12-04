package com.example.day34lab11blog.Service;

import com.example.day34lab11blog.ApiResponse.ApiException;
import com.example.day34lab11blog.Model.Comment;
import com.example.day34lab11blog.Model.Post;
import com.example.day34lab11blog.Model.UserAccount;
import com.example.day34lab11blog.Repository.CommentRepository;
import com.example.day34lab11blog.Repository.PostRepository;
import com.example.day34lab11blog.Repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserAccountRepository userAccountRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserAccountRepository userAccountRepository,
                          PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userAccountRepository = userAccountRepository;

    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        UserAccount userAccount = userAccountRepository.findUserAccountByUserId(comment.getUserId());
        Post post = postRepository.findPostById(comment.getPostId());
        if (userAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        if (post == null) {
            throw new ApiException("no post with this id was found");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Integer id,Comment comment) {
        UserAccount userAccount = userAccountRepository.findUserAccountByUserId(comment.getUserId());
        Post post = postRepository.findPostById(id);
        Comment oldComment = commentRepository.findCommentById(id);
        if (oldComment == null) {
            throw new ApiException("no comment with this id was found");
        }
        if (userAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        if (post == null) {
            throw new ApiException("no post with this id was found");
        }
        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());
        oldComment.setContent(comment.getContent());
//        oldComment.setCommentDate(LocalDateTime.now());
        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null) {
            throw new ApiException("no comment with this id was found");
        }
        commentRepository.delete(comment);
    }
}
