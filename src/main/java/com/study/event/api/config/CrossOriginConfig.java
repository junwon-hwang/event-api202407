package com.study.event.api.config;

// 전역 크로스 오리진 설정 : 어떤 클라이언트를 허용할 것인지

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {


    private String[] urls = {
              "http://localhost:3000",
              "http://localhost:3001",
              "http://localhost:3002",
              "http://localhost:3003",
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**") // 어떤 URL 요청에서
                .allowedOrigins(urls) // 어떤 클라이언트를
                .allowedMethods("*") // 어떤 방식에서 (GET , POST등)
                .allowedHeaders("*") // 어떤 헤더를 허용할지
                .allowCredentials(true) // 쿠키 전송을 허용할지
        ;
    }
}
