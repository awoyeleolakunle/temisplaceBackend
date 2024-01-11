package com.dansaki.com.temisplacebackend.services.blog.blogPostCreation;

import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.BlogCreationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.BlogPostUpdateRequest;
import com.dansaki.com.temisplacebackend.services.blog.blogPostService.BlogPostService;
import com.dansaki.com.temisplacebackend.services.blog.blogUpdate.UpdateBlogPostService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Service
@AllArgsConstructor
public class BlogPostCreationServiceImp implements BlogPostCreationService{

    private final BlogPostService blogPostService;
    private final UpdateBlogPostService updateBlogPostService;
    private final ModelMapper modelMapper;
    @Override
    public ApiResponse createBlogPost(BlogCreationRequest blogCreationRequest) {
        if(isAlreadyCreated(blogCreationRequest.getPostTitle())) {

            BlogPostUpdateRequest blogPostUpdateRequest = modelMapper.map(blogCreationRequest, BlogPostUpdateRequest.class);
           return updateBlogPostService.updateBlogPost(blogPostUpdateRequest);
        } else {
            LocalTime publishTime = parseIntoLocalTimeObject(blogCreationRequest);
            BlogPost blogPost = modelMapper.map(blogCreationRequest, BlogPost.class);
            blogPost.setBlogStatus(BlogStatus.ACTIVE);
            setPublishTimeAndSaveBlogPost(publishTime, blogPost);
            return GenerateApiResponse.createdResponse(GenerateApiResponse.POST_SUCCESSFULLY_CREATED);
        }
    }

    private void setPublishTimeAndSaveBlogPost(LocalTime publishTime, BlogPost blogPost) {
        blogPost.setPublishTime(publishTime);
        blogPostService.saveBlogPost(blogPost);
    }

    private LocalTime parseIntoLocalTimeObject(BlogCreationRequest blogCreationRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalTime.parse(blogCreationRequest.getPublishTime().toUpperCase(), formatter);
    }

    private boolean isAlreadyCreated(String postTitle) {
        return blogPostService.findByPostTitle(postTitle).isPresent();
    }
}
