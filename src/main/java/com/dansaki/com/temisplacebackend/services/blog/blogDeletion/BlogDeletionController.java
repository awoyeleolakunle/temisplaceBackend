package com.dansaki.com.temisplacebackend.services.blog.blogDeletion;


import com.dansaki.com.temisplacebackend.services.blog.blogPostService.BlogPostService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BlogDeletionController {

    private final BlogPostService blogPostService;

    @DeleteMapping("blogPostDeletion")
    public ResponseEntity<ApiResponse> deleteBlogPostById(@RequestParam Long id){

        return new ResponseEntity<>(blogPostService.deleteBlogPostById(id), HttpStatus.OK);

    }
}
