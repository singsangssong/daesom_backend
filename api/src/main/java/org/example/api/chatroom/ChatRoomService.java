package org.example.api.chatroom;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.database.chatroom.ChatRoom;
import org.example.database.chatroom.dto.ChatRoomDto;
import org.example.database.chatroom.dto.CreateRoomReq;
import org.example.database.chatroom.repository.ChatRoomRepo;
import org.example.database.jointable.ChatRoom_Member;
import org.example.database.jointable.repository.Room_MemberRepo;
import org.example.database.member.Member;
import org.example.database.member.repository.MemberRepo;
import org.example.database.redis.RedisSub;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepo chatRoomRepo;
    private final MemberRepo memberRepo;
    private final Room_MemberRepo roomMemberRepo;

    private final RedisSub redisSub;

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoomDto> opsHashMessageRoom;
    private Map<String, ChannelTopic> topics;

    @PostConstruct
    private void init() {
        opsHashMessageRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    @Transactional
    public void createRoom(CreateRoomReq createRoomReq) {
        List<ChatRoom_Member> chatRoomMembers = new ArrayList<>();
        ChatRoom chatRoom = chatRoomRepo.save(new ChatRoom(createRoomReq.getRoomName()));

        // user존재하는지 확인하고, 있으면 방만드는 jointable 리스트에 추가
        createRoomReq.getMemberIds().stream().forEach(memberId -> {
            Member member = memberRepo.findById(memberId).orElseThrow(RuntimeException::new);
            chatRoomMembers.add(new ChatRoom_Member(chatRoom.getId(), memberId, member, chatRoom));
        });

        roomMemberRepo.saveAll(chatRoomMembers);
    }


    // 채팅방 입장
    public void enterChatRoom(String chatRoomID) {
        ChannelTopic topic = topics.get(chatRoomID);

        if(topic == null) {
            topic = new ChannelTopic(chatRoomID);
            redisMessageListenerContainer.addMessageListener(redisSub, topic);
            topics.put(chatRoomID, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }


}
