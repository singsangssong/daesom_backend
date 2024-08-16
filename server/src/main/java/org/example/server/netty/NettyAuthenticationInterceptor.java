package org.example.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyAuthenticationInterceptor extends ChannelInboundHandlerAdapter {
    private final String secretKey;
    public NettyAuthenticationInterceptor(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 다음 핸들러로 메시지를 전달
        ctx.fireChannelRead(msg);
    }
}
