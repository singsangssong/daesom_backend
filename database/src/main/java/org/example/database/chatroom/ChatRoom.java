package org.example.database.chatroom;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.common.BaseEntity;
import org.example.database.member.Member;

@Entity
@Table(name = "chatroom")
@NoArgsConstructor
@Getter
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @Column(nullable = false)
    private String name;


//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

//    @Builder
//    public ChatRoom(String name , ) {
//
//    }

}
