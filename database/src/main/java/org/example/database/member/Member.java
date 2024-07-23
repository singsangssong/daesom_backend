package org.example.database.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.common.BaseEntity;
import org.example.database.member.dto.RegisterReq;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String id;

    private String name;

    private String password;

//    @OneToMany(mappedBy = "member")
//    List<ChatRoom> chatRooms = new ArrayList<>();

    @Builder
    public Member(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
