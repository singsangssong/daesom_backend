package org.example.database;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class ChatReq {
    private String msg;
    private Long roomId;
}
