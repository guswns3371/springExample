package com.example.springExample.domain.posts;

import com.example.springExample.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// @NoArgsConstructor
// 기본 생성자 자동 추가하는 롬복 어노테이션
@NoArgsConstructor

// @Getter
// 클래스 내 모든 필드의 Getter 메소드를 자동 생성해주는 롬복 어노테이션
@Getter
/**
 * Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
 * 해당 필드 값의 벼경이 필요한 경우, 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야한다.
 * Setter없이 생성자를 통해 최종값을 채운 뒤 DB에 삽입한다. (@Builder)
 * */

// @Entity : JPA 어노테이션
// Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이다. 보통 Entity 클래스라고 불린다.
// 기본적으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭한다.
// 예를 들어, SalesManager.java -> sales_manager table 로 매칭한다.
// Entity 클래스로 DB에 접근하게 해주는 JpaRepository를 생성해야한다.(PostsRepository.java)
@Entity
public class Posts extends BaseTimeEntity {

    // @Id : 해당 테이블의 primary key 필드를 나타낸다.
    @Id
    // @GeneratedValue : PK의 생성규칙을 나타낸다.
    // 스프링 부트 2.0에서는 IDENTITY 옵션을 추가해야지만 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 왠만하면 PK는 Long 타입의 auto_increment를 추천한다.
    private Long id;

    // @Column
    // 해당 어노테이션을 굳이 선언하지 않아도 Entity 클래스의 모든 필드는 칼럼이 된다
    // 기본값 이외에 추가로 변경이 필요한 옵션이 있을 경우 사용한다.
    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // @Builder
    // 해당 클래스의 빌더 패턴 클래스를 생성하는 어노테이션
    // 단, 생성자에 포함된 필드만 빌더에 포함한다.
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
