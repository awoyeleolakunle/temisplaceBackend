package com.dansaki.com.temisplacebackend.services.blog.blogUpdate;

import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.BlogPostUpdateRequest;
import com.dansaki.com.temisplacebackend.services.blog.blogPostService.BlogPostService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateBlogPostServiceImp implements UpdateBlogPostService{

    private final BlogPostService blogPostService;
    @Override
    public ApiResponse updateBlogPost(Long id, BlogPostUpdateRequest blogPostUpdateRequest) {
       Optional<BlogPost> blogPost = blogPostService.findPostById(id);
       if(blogPost.isEmpty()) return GenerateApiResponse.blogPostNotFound(GenerateApiResponse.BLOG_POST_NOT_FOUND);
       updatePost(blogPost.get(), blogPostUpdateRequest);
        return GenerateApiResponse.updateSucessful(GenerateApiResponse.STATUS_UPDATED_SUCCESSFULLY);
    }

    private void updatePost(BlogPost blogPost, BlogPostUpdateRequest blogPostUpdateRequest) {
        if(blogPostUpdateRequest.getBlogContent()!=null){
            blogPost.setBlogContent(blogPostUpdateRequest.getBlogContent());}
        if(blogPostUpdateRequest.getPostTitle()!=null){
            blogPost.setPostTitle(blogPostUpdateRequest.getPostTitle());
        }
        if(blogPostUpdateRequest.getPostImageUrl()!=null){
            blogPost.setPostImageUrl(blogPostUpdateRequest.getPostImageUrl());
        }
         blogPostService.saveBlogPost(blogPost);
    }
}
