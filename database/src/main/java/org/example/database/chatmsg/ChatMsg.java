package org.example.database.chatmsg;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.database.chatmsg.dto.MsgDto;
import org.example.database.common.BaseEntity;

@Entity
@Table(name = "chat_msg")
@Getter
@NoArgsConstructor
public class ChatMsg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long roomId;

    private String msg;

    @Builder
    public ChatMsg(MsgDto msgDto) {
        this.userId = msgDto.getSenderId();
        this.roomId = msgDto.getRoomId();
        this.msg = msgDto.getMsg();
    }
}
