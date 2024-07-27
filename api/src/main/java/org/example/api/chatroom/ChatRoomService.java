package org.example.api.chatroom;

import lombok.RequiredArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.chatroom.dto.CreateRoomReq;
import org.example.database.chatroom.repository.ChatRoomRepo;
import org.example.database.jointable.ChatRoom_Member;
import org.example.database.jointable.repository.Room_MemberRepo;
import org.example.database.member.Member;
import org.example.database.member.repository.MemberRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepo chatRoomRepo;
    private final MemberRepo memberRepo;
    private final Room_MemberRepo roomMemberRepo;

    @Transactional
    public void createRoom(CreateRoomReq createRoomReq) {
        List<ChatRoom_Member> chatRoomMembers = new ArrayList<>();
        ChatRoom chatRoom = chatRoomRepo.save(new ChatRoom(createRoomReq.getRoomName()));

        // user존재하는지 확인하고, 있으면 방만드는 jointable 리스트에 추가
        createRoomReq.getUserIdList().stream().forEach(memberId -> {
            Member member = memberRepo.findById(memberId).orElseThrow(RuntimeException::new);
            chatRoomMembers.add(new ChatRoom_Member(chatRoom.getId(), memberId, member, chatRoom));
        });

        roomMemberRepo.saveAll(chatRoomMembers);
    }
}
