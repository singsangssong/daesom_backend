package org.example.database.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.common.BaseEntity;
import org.example.database.jointable.ChatRoom_Member;
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
    private Long id;

    private String _id;

    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    List<ChatRoom_Member> chatRooms = new ArrayList<>();

    @Builder
    public Member(String _id, String name, String password) {
        this._id = _id;
        this.name = name;
        this.password = password;
    }

}
