package org.example.database.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.database.chatroom.ChatRoom;

import java.io.Serializable;

@Getter
public class ChatRoomDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roomId;
    private String roomName;
//    private Long ownerId;

    public ChatRoomDto (ChatRoom chatRoom) {
        this.roomName = chatRoom.getName();
        this.roomId = chatRoom.getId().toString();
//        this.ownerId =
    }
}
