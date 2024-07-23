package org.example.api.chatroom;

import lombok.RequiredArgsConstructor;
import org.example.database.chatroom.dto.CreateRoomReq;
import org.example.database.chatroom.repository.ChatRoomRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepo chatRoomRepo;

//    public void createRoom(CreateRoomReq createRoomReq) {
//        chatRoomRepo.
//
//    }
}
