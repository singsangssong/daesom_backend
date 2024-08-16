package org.example.database.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.chatmsg.dto.MsgDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSub implements MessageListener{

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String pubMsg = redisTemplate.getStringSerializer().deserialize(message.getBody());
            MsgDto msgReq = objectMapper.readValue(pubMsg, MsgDto.class);


            log.info(msgReq.getMsg() + ": 메세지 내용");
            log.info(msgReq.getRoomId() + ": 방번호");
            log.info("gkdlgkdlglahlsdgpa");

            redisTemplate.convertAndSend()

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
