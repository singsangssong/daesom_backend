package org.example.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
@RequiredArgsConstructor
public class NettySocketServer {

    private final ServerBootstrap serverBootstrap;
    private final InetSocketAddress tcpPort;
    private Channel serverChannel;
    public void start() {
        try {
            // bind메소드 -> socket을 열고, 동시에 bind, listen상태로 만듬.
            ChannelFuture serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
            // 채널이 닫히기 전까지 현재 쓰레드를 블록함. + 서버가 실행되는동안 클라이언트의 연결을 기다림.
            serverChannel = serverChannelFuture.channel().closeFuture().sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void stop() {
        if (serverChannel != null) {
            serverChannel.close();
            serverChannel.parent().closeFuture();
        }
    }
}
