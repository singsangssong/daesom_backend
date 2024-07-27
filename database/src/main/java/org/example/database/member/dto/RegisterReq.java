package org.example.database.member.dto;


import lombok.Getter;
import org.example.database.member.Member;

@Getter
public class RegisterReq {
    private String _id;
    private String name;
    private String password;

    public Member toEntity(RegisterReq registerReq) {
        return Member.builder()
                ._id(registerReq.get_id())
                .name(registerReq.getName())
                .password(registerReq.getPassword())
                .build();
    }
}
