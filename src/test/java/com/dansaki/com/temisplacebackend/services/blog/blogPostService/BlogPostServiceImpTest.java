package com.dansaki.com.temisplacebackend.services.blog.blogPostService;

import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.dtos.request.BlogCreationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class BlogPostServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllBlogPost() throws Exception {



        BlogCreationRequest blogCreationRequest = new BlogCreationRequest();
        blogCreationRequest.setPublishTime("10:00 AM");
        blogCreationRequest.setPublishDate(LocalDate.parse("2023-10-10"));
        blogCreationRequest.setBlogContent("Blog content");
        blogCreationRequest.setPostTitle("postTitle");
        blogCreationRequest.setBlogStatus(BlogStatus.ACTIVE);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/blog/blogPostCreation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blogCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));


        PaginationRequest paginationRequest = new PaginationRequest();
        paginationRequest.setPageNumber(0);
        paginationRequest.setPageSize(5);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/allBlogPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paginationRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
}