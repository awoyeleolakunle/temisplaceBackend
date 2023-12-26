package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.models.BlogPost;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    Optional<BlogPost> findByPostTitleEqualsIgnoreCase(String postTitle);
    //BlogPost findAllByBlogStatusActive(PaginationRequest paginationRequest);

    List<BlogPost> findAllByBlogStatus(BlogStatus blogStatus);
}
