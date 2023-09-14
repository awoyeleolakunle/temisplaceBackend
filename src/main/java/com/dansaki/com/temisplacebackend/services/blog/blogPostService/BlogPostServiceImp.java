package com.dansaki.com.temisplacebackend.services.blog.blogPostService;

import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.data.repositories.BlogPostRepository;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BlogPostServiceImp implements BlogPostService{

    private final BlogPostRepository blogPostRepository;
    @Override
    public BlogPost saveBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public Optional<BlogPost> findByPostTitle(String postTitle) {
        return blogPostRepository.findByPostTitleEqualsIgnoreCase(postTitle);
    }

    @Override
    public Optional<BlogPost> findPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    @Override
    public List<BlogPost> findAllBlogPost(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        Page<BlogPost> pages =  blogPostRepository.findAll(pageable);

        return pages.getContent();
    }
}

