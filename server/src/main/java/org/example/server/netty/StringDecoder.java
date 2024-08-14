package org.example.server.netty;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.chatmsg.dto.MsgDto;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StringDecoder extends ByteToMessageDecoder {

    private final ObjectMapper mapper;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        while (in.isReadable()) {
            int length = in.readableBytes();
            byte[] bytes = new byte[length];
            in.readBytes(bytes);
            String message = new String(bytes, StandardCharsets.UTF_8);
            MsgDto msgDto = mapper.readValue(message, MsgDto.class);
            out.add(msgDto);
        }
    }
}
