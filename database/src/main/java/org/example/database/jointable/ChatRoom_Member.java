package org.example.database.jointable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.member.Member;

@Entity
@Table(name = "chatroom_member")
@NoArgsConstructor
@Getter
public class ChatRoom_Member {
    @Id
    @GeneratedValue
    private Long id;

//    @JoinColumn
//    private ChatRoom chatRoom;
//
//    @JoinColumn
//    private Member member;
}
