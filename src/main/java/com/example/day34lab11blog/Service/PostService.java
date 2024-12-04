package com.example.day34lab11blog.Service;

import com.example.day34lab11blog.ApiResponse.ApiException;
import com.example.day34lab11blog.Model.Category;
import com.example.day34lab11blog.Model.Post;
import com.example.day34lab11blog.Model.UserAccount;
import com.example.day34lab11blog.Repository.CategoryRepository;
import com.example.day34lab11blog.Repository.PostRepository;
import com.example.day34lab11blog.Repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserAccountRepository userAccountRepository;
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, UserAccountRepository userAccountRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userAccountRepository = userAccountRepository;
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        UserAccount tempUserAccount = userAccountRepository.findUserAccountByUserId(post.getUserId());
        Category tempCategory = categoryRepository.findCategoriesByCategoryId(post.getCategoryId());
        if (tempUserAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        if (tempCategory == null) {
            throw new ApiException("no category with this id was found");
        }
//        post.setPublishDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public void updatePost(Integer id,Post post) {

        Post tempPost = postRepository.findPostById(id);
        if (tempPost == null) {
            throw new ApiException("no post with this id was found");
        }
        UserAccount tempUserAccount = userAccountRepository.findUserAccountByUserId(post.getUserId());
        Category tempCategory = categoryRepository.findCategoriesByCategoryId(post.getCategoryId());
        if (tempUserAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        if (tempCategory == null) {
            throw new ApiException("no category with this id was found");
        }
        tempPost.setTitle(post.getTitle());
        tempPost.setContent(post.getContent());
        tempPost.setCategoryId(post.getCategoryId());
        tempPost.setUserId(post.getUserId());
        postRepository.save(tempPost);
    }

    public void deletePost(Integer id) {
        Post tempPost = postRepository.findPostById(id);
        if (tempPost == null) {
            throw new ApiException("no post with this id was found");
        }
        postRepository.delete(tempPost);
    }

    public List<Post> getPostsByUserId(Integer userId) {
        UserAccount tempUserAccount = userAccountRepository.findUserAccountByUserId(userId);
        if (tempUserAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        List<Post> posts = postRepository.getAllPostsByUserId(userId);
        if (posts.isEmpty()) {
            throw new ApiException("no posts with this id was found");
        }
        return posts;
    }

    public Post getPostByTitle(String title) {
        Post post = postRepository.findPostByTitle(title);
        if (post == null) {
            throw new ApiException("no post with this title was found");
        }
        return post;
    }

    public List<Post> getPostsContainTitle(String title) {
    List<Post> posts = postRepository.findPostByTitleContaining(title);
    if (posts.isEmpty()) {
        throw new ApiException("no posts with this word in title was found");
    }
    return posts;
    }

    public List<Post> getPostsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Post> posts = postRepository.getPostsByPublishDateRange(start, end);
        if (posts.isEmpty()) {
            throw new ApiException("no posts with this publish date range was found");
        }
        return posts;
    }

    public List<Post> getPostsByCategoryId(Integer categoryId) {
        List<Post> posts = postRepository.findPostByCategoryId(categoryId);
        if (posts.isEmpty()) {
            throw new ApiException("no posts with this category was found");
        }
        return posts;
    }

    public Integer countPostsByUserId(Integer userId) {
    Integer count = postRepository.countPostByUserId(userId);
    if (count == 0) {
        throw new ApiException("no posts with this id was found");
    }
    return count;
    }

    public Post getRecentPostByUserId(Integer userId) {

        Post post = postRepository.getTheMostRecentPostByUserId(userId);
        if (post == null) {
            throw new ApiException("no post with this id was found");
        }
        return post;
    }

}
