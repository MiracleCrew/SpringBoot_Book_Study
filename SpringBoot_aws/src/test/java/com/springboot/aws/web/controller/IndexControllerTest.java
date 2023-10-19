package com.springboot.aws.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    /*
    * MockMvc
    * 컨테이너를 실행하지 않는다.
    * 서버의 입장에서 구현한 API를 통해 비지니스 로직에 문제가 없는지 테스트
    *
    * TestRestTemplate
    * 컨테이너를 직접 실행시킨다.
    * 클라이언트 입장에서 사용할 때 문제가 없는지 테스트할 때 사용
    * */
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}