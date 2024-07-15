package com.study.event.api.event.dto.request;


import lombok.*;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class LoginRequestDto {

    private String email;
    private String password;

    // 자동 로그인 여부...

}
