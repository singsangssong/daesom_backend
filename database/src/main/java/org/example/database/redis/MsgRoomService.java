package org.example.database.redis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.database.chatmsg.dto.MsgDto;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MsgRoomService {
    private final RedisTemplate<String, Object> redisTemplate;
    private Map<String, ChannelTopic> topics;

    private final RedisMessageListenerContainer messageListener;
    private final RedisSub redisSub;

    private HashOperations<String, String, MsgDto> opsHashMessageRoom;

    @PostConstruct
    private void init() {
        opsHashMessageRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    //TODO: 채팅방 입장하기 (리스너 추가하기)
    public void enterChatRoom(String roomId) {
        ChannelTopic channelTopic = topics.get(roomId);

        if(channelTopic == null) {
            channelTopic = new ChannelTopic(roomId);
            messageListener.addMessageListener(redisSub, channelTopic);
            topics.put(roomId, channelTopic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }
}
