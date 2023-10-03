package com.dansaki.com.temisplacebackend.services.blog.blogPostCreation;

import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.enums.BlogType;
import com.dansaki.com.temisplacebackend.data.enums.PublishStatus;
import com.dansaki.com.temisplacebackend.dtos.request.BlogCreationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc
class BlogPostCreationServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogPostCreationService blogPostCreationService;

    @Test
    public void testThatSuperAdminCanMakeABlogPost() throws Exception {
        BlogCreationRequest blogCreationRequest = new BlogCreationRequest();
        blogCreationRequest.setPostTitle("Food");
        blogCreationRequest.setBlogContent("The food is made of . lol me gan I don't know");
        blogCreationRequest.setBlogStatus(BlogStatus.valueOf("ACTIVE"));
        blogCreationRequest.setEmail("emailAddress");
        blogCreationRequest.setPublishDate(LocalDate.parse("2023-10-10"));
        blogCreationRequest.setPublishTime("10:00 PM");
        blogCreationRequest.setPublishStatus("PUBLISHED");
        blogCreationRequest.setBlogType("PAID");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/blog/blogPostCreation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}