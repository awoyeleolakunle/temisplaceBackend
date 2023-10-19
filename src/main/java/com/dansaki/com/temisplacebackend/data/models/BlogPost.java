package com.dansaki.com.temisplacebackend.data.models;


import com.dansaki.com.temisplacebackend.data.enums.BlogCategory;
import com.dansaki.com.temisplacebackend.data.enums.BlogStatus;
import com.dansaki.com.temisplacebackend.data.enums.BlogType;
import com.dansaki.com.temisplacebackend.data.enums.PublishStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postTitle;
    private String postAuthor;
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
    private BlogCategory blogCategory;
}
