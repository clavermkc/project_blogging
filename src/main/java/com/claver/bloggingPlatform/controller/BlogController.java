package com.claver.bloggingPlatform.controller;

import com.claver.bloggingPlatform.dto.BlogResponse;
import com.claver.bloggingPlatform.dto.CreateBlogRequest;
import com.claver.bloggingPlatform.dto.UpdateBlogRequest;
import com.claver.bloggingPlatform.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@Valid @RequestBody CreateBlogRequest createBlogRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(createBlogRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBlogRequest updateBlogRequest) {
        return ResponseEntity.ok(blogService.updateBlog(id, updateBlogRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<BlogResponse>> getBlogs(@RequestParam(required = false) String term) {
        return ResponseEntity.ok(blogService.getAllBlogs(term));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

}
