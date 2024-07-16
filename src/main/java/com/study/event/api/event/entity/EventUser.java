package com.study.event.api.event.entity;

//회원가입 요청 흐름
//
//  1st request: 이메일 중복확인 요청
//  1차: 이메일을 입력 -> 디바운싱을 통해 1.5초 뒤 서버로 이메일 중복확인 검증
//   -> 중복이 아니라고 판단되면 서버에서는 해당 이메일로 인증메일 발송
//
//  2nd request: 인증코드 검증 요청
//  2차: 인증코드 입력 -> 디바운싱을 통해 1.5초 뒤 서버로 인증코드 전송 검증
//   -> 만료시간 이내이고 인증코드가 일치한다면  -> 일치하지 않는다면 인증코드 재발송
//
//  3rd request: 회원가입 완료 요청
//  3차: 비밀번호 입력 -> 검증에 통과한다면 회원가입을 완료
//
//
//
//
//첫번째 테이블 : 회원 기본정보 테이블
//두번째 테이블 : 인증코드 정보 테이블 (인증코드, 만료시간)

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @ToString(exclude = "eventList")
@EqualsAndHashCode (of="id")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_event_user")
public class EventUser {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid-generator")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "ev_user_id")
    private String id; // 회원계정이 아니고 랜덤문자PK

    @Column(name= "ev_user_email", nullable = false, unique = true)
    private String email; // 회원계정

    // NN(NotNull)을 하지 않는 이유 : SNS 로그인 한 회원, 인증번호만 받고 회원가입을 완료하지 않은 사람
    @Column(length = 500)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Role role = Role.COMMON; // 권한

    private LocalDateTime createAt; // 회원가입 시간

    @OneToMany(mappedBy = "eventUser", orphanRemoval = true, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Event> eventList = new ArrayList<>();

    // 이메일 인증을 완료했는지 여부
    // 엔터티에 boolean타입을 사용하면 실제 DB에는 0,1로 저장됨에 주의
    @Setter
    @Column(nullable = false)
    private boolean emailVerified;

    public void confirm(String password) {
        this.password = password;
        this.createAt = LocalDateTime.now();
    }
}
