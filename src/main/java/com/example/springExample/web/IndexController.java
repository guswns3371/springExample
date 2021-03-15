package com.example.springExample.web;

import com.example.springExample.config.auth.CustomOAuth2UserService;
import com.example.springExample.config.auth.LoginUser;
import com.example.springExample.config.auth.dto.SessionUser;
import com.example.springExample.service.posts.PostsService;
import com.example.springExample.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/posts/save")
    public String postsSave() {
        // "/posts/save" 를 호출하면, posts-save.mustache 를 호출한다.
        return "posts-save";
    }

    // Model 객체
    // 서버 탬플릿 엔진에서 사용가능한 객체를 저장할 수 있다.
    // 해당 메소드에서는 postsService.findAllDesc()로 가져온 결과를 posts라는 이름으로 index.mustache에 전달한다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService 에서 로그인 성공시 세션에 SessionUser를 저장한다.
        // 다시 말해, 로그인 성공시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.
        // @LoginUser 어노테이션을 만들어서 반복되는 아래의 코드를 개선할 수 있다.
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 저장된 값이 있을 경우만 model에 userName으로 등록한다.
        if (user != null) {
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
