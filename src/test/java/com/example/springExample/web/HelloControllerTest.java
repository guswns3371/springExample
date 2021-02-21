package com.example.springExample.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//jsonPath, is관련 라이브러리를 직접 import해줘야 한다 (intellij에서 제대로 import안해줌)
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @RunWith(SpringRunner.class)
//  테스트 진행시 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다 (여기선 SpringRunner라는 스프링 실행자를 실행시킴)
//  다시말해, 스프링 부트 테스트와 JUnit 사이의 연결자 역할을 한다.
@RunWith(SpringRunner.class)
// @WebMvcTest
//  여러가지의 스프링 테스트 어노테이션 중에서 Web(Spring Mvc)에 집중할 수 있는 어노테이션이다.
//  @Controller,@ControllerAdvice를 사용할 수 있다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // @Autowired
    //  스프링이 관리하는 Bean을 주입 받는 역할을 한다.
    // private MockMvc mvc
    //  웹 API를 테스트할 경우에에 사용한. HTTP GET,POST 등에 대한 테스트가 능가
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // mvc.perform(get("/hello"))
        // '/hello' 주소로 GET 요청을 한다
        // andExpect(status().isOk())
        //  mvc.perform의 결과를 검증한다. OK(200)인지 검증
        // .andExpect(content().string(hello));
        //  응답 본문의 내용을 검증한다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        // param
        //      API 테스트에 사용될 요청 파리미터를 설정한다. String값만 허용된다.
        // jsonPath
        //      JSON 응답값을 필드별로 검증할 수 있는 메소드이다.
        //      $를 기준으로 필드명을 명시한다. ($.name, $.amount와 같이)
        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));


    }
}
