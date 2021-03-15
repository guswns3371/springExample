package com.example.springExample.web.dto;

import org.junit.Test;

// JUnit의 assertThat이 아니라 assertj의 assertThat이다.
//      assertj의 장점(JUnit과 비교하였을 때)
//          CoreMatchers와 다르게 추가적 라이브러리가 필요하지 않다.
//          자동완성이 좀더 확실하게 지원된다.
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name,amount);

        // assertThat
        //      assertj라는 테스트 검증 라이브러리의 검증 메소드이다.
        //      검증하고자 하는 대상을 인자로 받고, 메소드 체이닝을 활용하여 isEqualTo 메소드를 이어서 사용할 수 있다.
        //          isEqualTo : assertj의 동등 비교 메소드이다. assertThat의 인자값과 동일할 경우만 성공한다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
