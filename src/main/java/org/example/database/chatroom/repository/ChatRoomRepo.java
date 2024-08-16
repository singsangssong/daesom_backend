package org.example.database.chatroom.repository;

import org.example.database.chatroom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, Long> {
}
