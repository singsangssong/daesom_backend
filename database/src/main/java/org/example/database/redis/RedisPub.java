package org.example.database.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.chatmsg.dto.MsgDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPub {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic ch, MsgDto dto) {
        log.info("현재" + ch + "에 publish를 준비중입니다.");
        redisTemplate.convertAndSend(ch.getTopic(), dto);
    }
}
