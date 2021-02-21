package com.example.springExample.web;

import com.example.springExample.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// 컨트롤러를 JSON 을 반환하는 컨트롤러로 만들어준다.
@RestController
public class HelloController {

    // @GetMapping
    // HTTP Get Method 의 요청을 받을 수 있는 API를 만들어준다.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
