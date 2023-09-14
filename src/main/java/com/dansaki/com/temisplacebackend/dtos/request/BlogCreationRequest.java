package com.dansaki.com.temisplacebackend.dtos.request;


import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.enums.BlogType;
import com.dansaki.com.temisplacebackend.data.enums.PublishStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogCreationRequest {
    private String email;
    private String postTitle;
    private LocalDate publishDate;
    private String publishTime;
    private PublishStatus publishStatus;
    private String blogContent;
    private String postImageUrl;
    private BlogType blogType;
    private BlogStatus blogStatus;
}
