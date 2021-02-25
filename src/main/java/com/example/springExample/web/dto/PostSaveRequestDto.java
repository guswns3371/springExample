package com.example.springExample.web.dto;

import com.example.springExample.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Dto 클래스는 Controller와 Service에서 사용된다.
// Dto 클래스다(View layer)는 Entity 클래스(DB layer)와 유사하고, Request/Response 클래스로 사용된다.
// Entity 클래스를 Request/Response 클래스로 사용하면 안된다. 테이터베이스와 맞닿은 핵심 클래스이기 떄문이다.
// Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경된

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
