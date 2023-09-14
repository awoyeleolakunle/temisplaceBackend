package com.dansaki.com.temisplacebackend.controllers.blogPostController;


import com.dansaki.com.temisplacebackend.dtos.request.BlogCreationRequest;
import com.dansaki.com.temisplacebackend.services.blog.blogPostCreation.BlogPostCreationService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/temisplace/blog/")
@CrossOrigin(origins = "*")

public class BlogPostController {

    private final BlogPostCreationService blogPostCreationService;

    @PostMapping("blogPostCreation")
    public ResponseEntity<ApiResponse> createBlogPost(@RequestBody BlogCreationRequest blogCreationRequest){
        return new ResponseEntity<>(blogPostCreationService.createBlogPost(blogCreationRequest), HttpStatus.OK);
    }
}
