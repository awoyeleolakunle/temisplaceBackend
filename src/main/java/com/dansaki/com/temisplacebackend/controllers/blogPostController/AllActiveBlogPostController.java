package com.dansaki.com.temisplacebackend.controllers.blogPostController;


import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import com.dansaki.com.temisplacebackend.services.blog.blogPostService.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class AllActiveBlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping("allActiveBlogPosts")
    public ResponseEntity<List<BlogPost>> fetchAllActiveBlogPost(@RequestParam int pageSize, @RequestParam int pageNumber){
        return new ResponseEntity<>(blogPostService.findAllActiveBlogPost(pageSize, pageNumber), HttpStatus.OK);
    }
}
