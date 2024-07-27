package org.example.database.chatroom.dto;

import lombok.Getter;
import org.example.database.chatroom.ChatRoom;

import java.util.List;

@Getter
public class CreateRoomReq {
    private String roomName;
    private List<Long> userIdList;

//    public ChatRoom toEntity(String roomName, List<Long> userIdList) {
//
//
//        return ChatRoom.builder()
//                .name(roomName)
//                .memberIds(userIdList)
//                .build();
//    }

}
