package com.example.springExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing : JPA Auditing 활성화
/** JpaConfig 클래스에 @EnableJpaAuditing를 추가하고 여기선 삭제한다
 *  왜냐하면 @EnableJpaAuditing가 @SpringBootApplication와 함께 있으므로 @WebMvcTest가 스캔하기 때문이다. (JPAAuditing관련 에러발생)
 * */
//@EnableJpaAuditing
// @SpringBootApplication : 스프링 부투의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동을 설정해준다. (항상 프로젝트 상단에 위치해야함)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // SpringApplication.run 함수 : 내장 WAS(웹 어플리케이션 서버)를 실행한다. 외부의 별도 WAS(Tomcat 등)가 필요 없게된다
        // 스프링 부트로 만들어진 Jar 파일(실행가능한 Java 패키징 파일)로 실행하면 된다.
        // 내장 WAS는 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있다.
        SpringApplication.run(Application.class, args);
    }
}
