package org.example.database.redis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.database.chatmsg.ChatMsg;
import org.example.database.chatmsg.dto.MsgDto;
import org.example.database.chatmsg.repository.ChatMsgRepo;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MsgService {

    private final ChatMsgRepo chatMsgRepo;
    private final RedisTemplate<String, Object> redisTemplate;

    //TODO: 채팅방

    //TODO: 채팅방 대화 저장(redis & db)
    public void saveMsg(MsgDto msgReq) {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatMsg.class));
        redisTemplate.opsForList().rightPush(msgReq.getRoomId().toString(), msgReq);
    }

    //TODO: 채팅방 채팅내역 조회하기
//    public List<MsgDto> infoMsg(String roomId) {
//
//    }


}
