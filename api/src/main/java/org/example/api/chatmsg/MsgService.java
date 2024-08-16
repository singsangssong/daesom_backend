package org.example.api.chatmsg;

import lombok.RequiredArgsConstructor;
import org.example.database.chatmsg.ChatMsg;
import org.example.database.chatmsg.dto.MsgDto;
import org.example.database.chatmsg.repository.ChatMsgRepo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

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
