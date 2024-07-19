package org.example.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final StringHandler stringHandler;
    // 클라이언트 소켓 채널이 생성될 때 호출
    @Override
    protected void initChannel(SocketChannel ch) {
        final ChannelPipeline pipeline = ch.pipeline();

        // 뒤이어 처리할 디코더 및 핸들러 추가
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(stringHandler);
    }
}
