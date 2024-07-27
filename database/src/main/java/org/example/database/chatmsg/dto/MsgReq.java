package org.example.database.chatmsg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class MsgReq {
    private Long senderId;
    private Long roomId;
    private String msg;
}
