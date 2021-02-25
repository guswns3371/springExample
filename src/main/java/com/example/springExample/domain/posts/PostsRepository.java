package com.example.springExample.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동생성된다.
 * @Repository 어노테이션을 추가할 필요가 없다.
 * 또한 Entity 클래스와 Entity Repository는 함께 위치해있어야 한다.
 * */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
