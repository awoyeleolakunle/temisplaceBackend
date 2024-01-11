package com.dansaki.com.temisplacebackend.services.blog.blogPostService;

import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    BlogPost saveBlogPost(BlogPost blogPost);

    Optional<BlogPost> findByPostTitle(String postTitle);

    Optional<BlogPost> findPostById(Long id);

    List<BlogPost> findAllBlogPost(PaginationRequest paginationRequest);

    ApiResponse deleteBlogPostById(Long id);

    List<BlogPost> findAllActiveBlogPost(int pageSize, int pageNumber);
}
