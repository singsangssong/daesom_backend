package org.example.api.chatroom;

import lombok.RequiredArgsConstructor;
import org.example.database.chatroom.dto.CreateRoomReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("")
    public ResponseEntity<String> createChatRoom(@RequestBody CreateRoomReq createRoomReq) {
        chatRoomService.createRoom(createRoomReq);
        return ResponseEntity.ok("create room");
    }



}
