package com.study.event.api.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// QueryDsl 셋팅
@Configuration
public class QueryDslConfig {

    @PersistenceContext
    private EntityManager em;

    // "<bean id='jpaQueryFactory' class='com.querydsl.jpa.impl.JPAQueryFactory'>

    @Bean // 외부 라이브러리를 스프링 컨테이너에 관리시키는 설정
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }

}
