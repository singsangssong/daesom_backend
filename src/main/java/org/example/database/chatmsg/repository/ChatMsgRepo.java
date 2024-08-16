package org.example.database.chatmsg.repository;

import org.example.database.chatmsg.ChatMsg;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatMsgRepo extends JpaRepository<ChatMsg, Long> {
}
