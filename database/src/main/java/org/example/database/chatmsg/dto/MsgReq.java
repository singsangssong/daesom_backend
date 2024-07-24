package org.example.database.chatmsg.dto;

import lombok.Getter;

@Getter
public class MsgReq {
    private Long senderId;
    private Long roomId;
    private String msg;
}
