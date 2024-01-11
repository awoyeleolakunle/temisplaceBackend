package com.dansaki.com.temisplacebackend.controllers.blogPostController;


import com.dansaki.com.temisplacebackend.dtos.request.BlogPostStatusUpdateRequest;
import com.dansaki.com.temisplacebackend.services.blog.BlogPostStatusUpdate.BlogPostStatusUpdateService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BlogPostStatusUpdateController {

    private final BlogPostStatusUpdateService blogPostStatusUpdateService;

    @PatchMapping ("blogPostStatusUpdate")
    public ResponseEntity<ApiResponse> updateBlogPostBlogStatus(@RequestBody BlogPostStatusUpdateRequest blogPostStatusUpdateRequest){
        return new ResponseEntity<>(blogPostStatusUpdateService.updateBlogPostBlogStatus(blogPostStatusUpdateRequest), HttpStatus.OK);
    }
}
