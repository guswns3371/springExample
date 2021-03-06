package com.example.springExample.service.posts;

import com.example.springExample.domain.posts.Posts;
import com.example.springExample.domain.posts.PostsRepository;
import com.example.springExample.web.dto.PostSaveRequestDto;
import com.example.springExample.web.dto.PostsListResponseDto;
import com.example.springExample.web.dto.PostsResponseDto;
import com.example.springExample.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


// @RequiredArgsConstructor
// 생성자로 Bean 객체를 받도록하는 역할을한다
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성하는 어노테이션이다.
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    // update 부분에서 DB에 쿼리를 날리는 부분이 없다. (JPA의 영속성 컨텍스트 때문이다)
    // 영속성 컨텍스트 : 엔티티를 영구 저장하는 환경. 일종의 논리적 개념
    // JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있는지 아닌지에 갈린다.
    // JPA의 엔티티 메니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
    // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경된 내용을 반영한다.
    // 더티 체킹 : 엔티티 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 더티 체킹 : 엔티티 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // @Transactional 어노테이션에서 readOnly = true 조건은
    // 트랜잭션 범위는 유지하되 조회기능만 남겨두어 조회 속도가 개선된다.
    // 이 옵션은 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는게 바람직하다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {

        // postsRepository의 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto으로 변환하여 List 로 반환하는 메소드이다.
        return postsRepository.findAllDesc().stream()
                // .map(PostsListResponseDto::new) 는 .map(posts -> new PostsListResponseDto(posts))와 같은 의미이다.
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
