package com.example.springExample.web;

import com.example.springExample.service.posts.PostsService;
import com.example.springExample.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/posts/save")
    public String postsSave() {
        // "/posts/save" 를 호출하면, posts-save.mustache 를 호출한다.
        return "posts-save";
    }

    // Model 객체
    // 서버 탬플릿 엔진에서 사용가능한 객체를 저장할 수 있다.
    // 해당 메소드에서는 postsService.findAllDesc()로 가져온 결과를 posts라는 이름으로 index.mustache에 전달한다.
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
