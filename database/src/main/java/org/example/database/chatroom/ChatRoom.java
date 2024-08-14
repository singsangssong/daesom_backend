package org.example.database.chatroom;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatroom.dto.ChatRoomDto;
import org.example.database.common.BaseEntity;
import org.example.database.jointable.ChatRoom_Member;
import org.example.database.member.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom")
@NoArgsConstructor
@Getter
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "chatRoom")
    List<ChatRoom_Member> members = new ArrayList<>();

    @Builder
    public ChatRoom(String name) {
        this.name = name;
    }

    public ChatRoomDto toDto(ChatRoom chatRoom) {
        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom);
    }
}
