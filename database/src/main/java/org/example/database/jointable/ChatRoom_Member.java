package org.example.database.jointable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.member.Member;

@Entity(name = "chatroom_member")
@NoArgsConstructor
@Getter
public class ChatRoom_Member {
    @EmbeddedId
    private ChatRoomMemberPK pk;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("chatRoomId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public ChatRoom_Member(Long roomId, Long memberId, Member member, ChatRoom chatRoom) {
        this.pk = new ChatRoomMemberPK(roomId, memberId);
        this.member = member;
        this.chatRoom = chatRoom;
    }
}
