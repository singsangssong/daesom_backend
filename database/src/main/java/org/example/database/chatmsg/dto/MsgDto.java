package org.example.database.chatmsg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MsgDto {
    private Long senderId;
    private Long receiver;
    private Long roomId;
    private String msg;
}
