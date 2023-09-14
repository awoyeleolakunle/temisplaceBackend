package com.dansaki.com.temisplacebackend.services.blog.blogPostCreation;

import com.dansaki.com.temisplacebackend.dtos.request.BlogCreationRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface BlogPostCreationService {
    ApiResponse createBlogPost(BlogCreationRequest blogCreationRequest);
}
