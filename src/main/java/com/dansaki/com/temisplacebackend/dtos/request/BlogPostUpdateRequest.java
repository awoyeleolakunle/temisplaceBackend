package com.dansaki.com.temisplacebackend.dtos.request;


import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.enums.BlogType;
import com.dansaki.com.temisplacebackend.data.enums.PublishStatus;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostUpdateRequest {
    private String postTitle;
    private String email;
    private LocalDate publishDate;
    private LocalTime publishTime;
    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatus;
    private String blogContent;
    private String postImageUrl;
    @Enumerated(EnumType.STRING)
    private BlogType blogType;
    @Enumerated(EnumType.STRING)
    private BlogStatus blogStatus;
}
