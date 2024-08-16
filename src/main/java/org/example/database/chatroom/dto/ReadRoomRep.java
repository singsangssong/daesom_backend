package org.example.database.chatroom.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadRoomRep {
    private Long roomId;
    private String roomName;
    private String lastMsg;
    private LocalDateTime lastMsgTime;
}
