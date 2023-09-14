package com.dansaki.com.temisplacebackend.services.blog.blogUpdate;

import com.dansaki.com.temisplacebackend.dtos.request.BlogPostUpdateRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface UpdateBlogPostService {
    ApiResponse updateBlogPost(Long id, BlogPostUpdateRequest blogPostUpdateRequest);
}
