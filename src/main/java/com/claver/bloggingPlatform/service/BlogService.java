package com.claver.bloggingPlatform.service;

import com.claver.bloggingPlatform.dto.BlogResponse;
import com.claver.bloggingPlatform.dto.CreateBlogRequest;
import com.claver.bloggingPlatform.dto.UpdateBlogRequest;
import com.claver.bloggingPlatform.exception.BlogNotFoundException;
import com.claver.bloggingPlatform.model.Blog;
import com.claver.bloggingPlatform.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService  {
    private final BlogRepository blogRepository;
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    public BlogResponse createBlog(CreateBlogRequest createBlogRequest) {
        Blog blog = Blog.builder()
            .title(createBlogRequest.getTitle())
            .content(createBlogRequest.getContent())
            .category(createBlogRequest.getCategory())
            .tags(new ArrayList<>(List.of(createBlogRequest.getTags())))
            .build();
        return toBlogResponse(blogRepository.save(blog));
    }

    public BlogResponse updateBlog(Long id, UpdateBlogRequest updateBlogRequest) {
        Blog existingBlog = findBlogById(id);
        existingBlog.setTitle(updateBlogRequest.getTitle());
        existingBlog.setContent(updateBlogRequest.getContent());
        existingBlog.setCategory(updateBlogRequest.getCategory());
        existingBlog.setTags(new ArrayList<>(List.of(updateBlogRequest.getTags())));
        return toBlogResponse(blogRepository.save(existingBlog));
    }

    public void deleteBlog(Long id) {
        Blog blog = findBlogById(id);
        blogRepository.delete(blog);
    }

    public List<BlogResponse> getAllBlogs(String term) {
        List<Blog> blogs = (term == null || term.isBlank())
                ? blogRepository.findAll()
                : blogRepository.search(term);
        return blogs.stream().map(this::toBlogResponse).toList();
    }

    private BlogResponse toBlogResponse(Blog blog) {
       return   BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .category(blog.getCategory())
                .tags(List.copyOf(blog.getTags()))
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }

    public BlogResponse getBlogById(Long id) {
        return toBlogResponse(findBlogById(id));
    }

    private Blog findBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
    }
}
