package org.example.database.chatroom.dto;

import lombok.Getter;
import org.example.database.chatroom.ChatRoom;

import java.util.List;

@Getter
public class CreateRoomReq {
    String roomName;
    List<String> userIdList;

//    public ChatRoom toEntity(CreateRoomReq createRoomReq) {
//        return ChatRoom
//    }
}
