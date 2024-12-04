package com.example.day34lab11blog.Repository;

import com.example.day34lab11blog.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    // get all posts from this user id
    @Query("select p from Post p where p.id = ?1")
    List<Post> getAllPostsByUserId(Integer userId);

    // get post by title
    Post findPostByTitle(String title);

    // get all posts that contain ... in title
    List<Post> findPostByTitleContaining(String title);

    // get all posts in fate range
    @Query("select p from Post p where p.publishDate >= ?1 and p.publishDate <= ?2")
    List<Post> getPostsByPublishDateRange(LocalDateTime start, LocalDateTime end);

    // get posts by categoryID
    List<Post> findPostByCategoryId(Integer categoryId);

    // get the number of posts by a user
    Integer countPostByUserId(Integer categoryId);

    // get the most recent post by a user
    @Query("select p from Post p where p.userId =?1 order by p.publishDate desc ")
    Post getTheMostRecentPostByUserId(Integer userId);
}
