package com.actudy.web.config.auth;

import com.actudy.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @EnableWebSecurity
 * Spring Security 설정 활성화
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *  CSRF (Cross Site Request Forgery)
         *   - 사이트 간 요청 위조
         *   - 일반 사용자의 의도와 다르게 공격자에 의도한 행위를 요청하게 만드는 보내는 행위
         *   - 본 예제에서는 h2-console 접속을 위해 disable 설정
         *     http.csrf().disable()
         *
         *  X-Frame-Options
         *   - X-Frame-Options 응답 헤더는 웹 페이지의 http 응답의 일부로 전달
         *   - 브라우저가 <FRAME> 또는 <IFRAME> 태그 내에서 페이지를 렌더링하도록 허용하는지 여부
         *   - 허용 범위
         *    DENY : 어떤 도메인도 프레임 내 페이지를 표시하는 것을 허용하지 않는다.
         *    SAMEORIGIN : 현재 도메인에서만 현재 페이지의 프레임을 허용한다.
         *    ALLOW_FROM URI : 특정 URI에만 허용한다.
         *   - 본 예제에서는 h2-console 접속을 위해 disable 설정
         *     http.headers().frameOptions().disable()
         *
         *  X-Frame-Options Clickjacking
         *   - 사용자가 보이지 않거나 다른 요소로 위장한 웹페이지 요소를 클릭하도록 속이는 공격
         *   - 사용자는 자신도 모르게 악성 코드 다운로드나, 웹 페이지 방문, 계좌 이체 등을 수행해 버릴 수 있다.
         *   - <IFRAME>은 HTML 내부에 또 다른 HTML을 삽입하는 방식임을 기억하자.
         */
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                // URL별 권한 관리 설정
                .authorizeRequests()
                // 권한 관리 대상 지정 (URL, HTTP 메소드별로 관리)
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile")
                .permitAll() // 전체 열람 권한
                .antMatchers("/api/v1/**")
                .hasRole(Role.USER.name()) // 역할이 USER인 경우 허용
                .anyRequest() // 설정된 값 이외의 URL
                .authenticated()
                .and()

                // 로그아웃 기능에 대한 설정
                .logout()
                .logoutSuccessUrl("/") // 성공 시 "/"로 이동
                .and()

                // OAuth2 로그인 성공 이후 사용저 정보를 가져올 때의 설정
                .oauth2Login()
                .userInfoEndpoint()
                // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
                .userService(customOAuth2UserService);
    }
}