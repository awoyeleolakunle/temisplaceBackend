package com.dansaki.com.temisplacebackend.services.blog.BlogPostStatusUpdate;


import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.BlogPostStatusUpdateRequest;
import com.dansaki.com.temisplacebackend.services.blog.blogPostService.BlogPostService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogPostStatusUpdateServiceImp implements BlogPostStatusUpdateService {

    private final BlogPostService blogPostService;

    @Override
    public ApiResponse updateBlogPostBlogStatus(BlogPostStatusUpdateRequest blogPostStatusUpdateRequest) {
        Optional<BlogPost> blogPost = blogPostService.findPostById(blogPostStatusUpdateRequest.getId());
        if (blogPost.isPresent()) {
            blogPost.get().setBlogStatus(BlogStatus.valueOf(blogPostStatusUpdateRequest.getBlogPostStatus().toUpperCase()));
            blogPostService.saveBlogPost(blogPost.get());
            return GenerateApiResponse.UpdateStatus(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
        }else{
            return GenerateApiResponse.blogPostNotFound(GenerateApiResponse.BLOG_POST_NOT_FOUND);
        }
    }
}
