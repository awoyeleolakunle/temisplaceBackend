package com.dansaki.com.temisplacebackend.services.blog.BlogPostStatusUpdate;

import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.BlogPostStatusUpdateRequest;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;

public interface BlogPostStatusUpdateService {
    ApiResponse updateBlogPostBlogStatus(BlogPostStatusUpdateRequest blogPostStatusUpdateRequest);
}
