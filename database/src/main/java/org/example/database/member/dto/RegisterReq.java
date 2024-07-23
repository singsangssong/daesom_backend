package org.example.database.member.dto;


import lombok.Getter;
import org.example.database.member.Member;

@Getter
public class RegisterReq {
    private String id;
    private String name;
    private String password;

    public Member toEntity(RegisterReq registerReq) {
        return Member.builder()
                .id(registerReq.getId())
                .name(registerReq.getName())
                .password(registerReq.getPassword())
                .build();
    }
}
