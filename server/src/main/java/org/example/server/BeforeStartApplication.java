package org.example.server;

import lombok.RequiredArgsConstructor;
import org.example.server.netty.NettySocketServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeforeStartApplication implements ApplicationListener<ApplicationReadyEvent> {

    private final NettySocketServer nettySocketServer;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent readyEvent) {
        nettySocketServer.start();
    }
}
