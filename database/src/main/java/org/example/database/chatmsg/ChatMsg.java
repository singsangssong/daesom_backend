package org.example.database.chatmsg;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.database.common.BaseEntity;

@Entity
@Table(name = "chat_msg")
@Getter
public class ChatMsg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long roomId;

    private String msg;

}
