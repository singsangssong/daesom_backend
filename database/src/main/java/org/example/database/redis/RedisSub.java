package org.example.database.redis;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.chatmsg.dto.MsgReq;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSub implements MessageListener {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String pubMsg = redisTemplate.getStringSerializer().deserialize(message.getBody());
            MsgReq msgReq = objectMapper.readValue(pubMsg, MsgReq.class);

            log.info(msgReq + "를 subscriber가 받았습니다!");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
