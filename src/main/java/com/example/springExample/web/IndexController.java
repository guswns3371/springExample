package com.example.springExample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // "/"를 호출하면, index.mustache 가 호출된다.
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave() {

        // "/posts/save" 를 호출하면, posts-save.mustache 를 호출한다.
        return "posts-save";
    }
}
