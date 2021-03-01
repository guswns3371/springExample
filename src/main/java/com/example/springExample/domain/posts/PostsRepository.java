package com.example.springExample.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동생성된다.
 * @Repository 어노테이션을 추가할 필요가 없다.
 * 또한 Entity 클래스와 Entity Repository는 함께 위치해있어야 한다.
 * */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa 에서 제공하지 않은 메소드는 @Query 어노테이션을 사용하여 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC ")
    List<Posts> findAllDesc();
}
