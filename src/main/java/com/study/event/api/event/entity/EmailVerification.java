package com.study.event.api.event.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode (of= "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name ="tbl_email_verification")
public class EmailVerification {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid-generator")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "ev_verification_id")
    private String id;

    @Column(nullable = false)
    private String verificationCode; // 인증코드

    @Column(nullable = false)
    private LocalDateTime expiryDate; // 인증 만료시간

    @OneToOne
    // 이름이 서로 다를 경우
    @JoinColumn(name = "ev_user_id",referencedColumnName = "ev_user_id")
    private EventUser eventUser;

    /*
        ALTER TABLE tbl_email verification
        ADD CONSTRAINT fk_dfsd_dfsdfsdf
        FOREIGN KEY (event_user_id)
        REFERENCES tbl_event_user (ev_user_id)

     */


}


