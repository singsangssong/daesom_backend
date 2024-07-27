package org.example.database.jointable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.member.Member;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class ChatRoomMemberPK implements Serializable {

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;
}
