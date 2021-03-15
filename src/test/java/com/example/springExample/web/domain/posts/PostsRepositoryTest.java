package com.example.springExample.web.domain.posts;

import com.example.springExample.domain.posts.Posts;
import com.example.springExample.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
// @SpringBootTest 어노테이션을 사용할 경우 자동으로 H2 데이터베이스를 실행해준다.
// 만약 yaml 파일에 다른 DB에 관한 configuration 이 적혀있다면 오류가 발생할 가능성이 매우 크다.
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;


    // @After
    // JUnit 단위 테스트가 끝날 떄 마다 수행되는 메소드를 지정한다.
    // 배포 전 전체 테스트를 수행할 경우에 테스트간 데이터 침범을 막는 용도로 사용한다.
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // postsRepository.save
        // 테이블 posts에 insert/update 쿼리를 실행한다.
        // id값이 있다면 -> update가 실행되고
        // id값이 없다면 -> insert가 실행된다.
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("guswns3371@gmail.com")
                .build()
        );

        // postsRepository.findAll()
        // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드이다.
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2021,2,25,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createdDate="+posts.getCreatedData()+", modifiedDate="+posts.getModifiedData());

        assertThat(posts.getCreatedData()).isAfter(now);
        assertThat(posts.getModifiedData()).isAfter(now);
    }
}
