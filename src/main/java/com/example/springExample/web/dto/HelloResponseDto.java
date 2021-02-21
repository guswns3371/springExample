package com.example.springExample.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// @Getter : 선언된 모든 필드의 get 메소드를 생성해준다 (getter 메소드를 내가 생성할 필요 없다)
@Getter
// @RequiredArgsConstructor
//      선언된 모든 final 필드가 포함된 생성자를 생성해준다.(final이 없는 필드는 생성하지 않는다.)
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
