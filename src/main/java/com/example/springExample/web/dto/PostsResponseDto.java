package com.example.springExample.web.dto;

import com.example.springExample.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    // final 키워드는 엔티티를 한 번만 할당한다. 즉, 두 번 이상 할당하려 할 때 컴파일 오류가 발생하여 확인이 가능
    private final Long id;
    private final String title;
    private final String content;
    private final String author;


    // PostsResponseDto는 entity의 일부분만 사용하기 떄문에 생성자로 entity를 받아 필드에 갚을 넣는다.
    // 모든 필드를 갖는 생성자가 필요하지 않기 떄문에 entity를 받아 처리한다.
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
