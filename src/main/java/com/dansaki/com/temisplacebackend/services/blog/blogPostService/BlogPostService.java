package com.dansaki.com.temisplacebackend.services.blog.blogPostService;

import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    BlogPost saveBlogPost(BlogPost blogPost);

    Optional<BlogPost> findByPostTitle(String postTitle);

    Optional<BlogPost> findPostById(Long id);

    List<BlogPost> findAllBlogPost(PaginationRequest paginationRequest);

}
