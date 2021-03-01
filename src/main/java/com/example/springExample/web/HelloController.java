package com.example.springExample.web;

import com.example.springExample.web.dto.HelloResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// 컨트롤러를 JSON 을 반환하는 컨트롤러로 만들어준다.
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // @GetMapping
    // HTTP Get Method 의 요청을 받을 수 있는 API를 만들어준다.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // @RequestParam
    //      외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/jenkins")
    public String jenkins_test() {
        logger.trace("Trace Level Test");
        logger.debug("DEBUG Level Test");
        logger.info("INFO Level Test");
        logger.warn("Warn Level Test");
        logger.error("ERROR Level Test");

        return "jenkins test : guswns3371/spring-example:1.0-SNAPSHOT";
    }
}
