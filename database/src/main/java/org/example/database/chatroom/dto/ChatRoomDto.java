package org.example.database.chatroom.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.database.chatroom.ChatRoom;

@Getter

public class ChatRoomDto {
    private String roomId;
    private String roomName;
//    private Long ownerId;

    public ChatRoomDto (ChatRoom chatRoom) {
        this.roomName = chatRoom.getName();
        this.roomId = chatRoom.getId().toString();
//        this.ownerId =
    }
}
