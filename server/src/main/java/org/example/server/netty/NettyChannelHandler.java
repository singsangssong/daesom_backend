package org.example.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.chatmsg.dto.MsgReq;
import org.example.database.redis.RedisPub;
import org.example.server.MsgService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@io.netty.channel.ChannelHandler.Sharable
@RequiredArgsConstructor
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {

    private int DATA_LENGTH = 1024;
    private ByteBuf buff;

    private final MsgService msgService;
    private final RedisPub redisPub;
    // 핸들러가 생성될 때 호출되는 메소드
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("get a buffer!...");
        buff = ctx.alloc().buffer(DATA_LENGTH);
    }

    // 핸들러가 제거될 때 호출되는 메소드
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("release buffer...");
        if (buff != null) {
            ReferenceCountUtil.safeRelease(buff);
            buff = null;
        }
    }

    // 클라이언트와 연결되어 트래픽을 생성할 준비가 되었을 때 호출되는 메소드
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String remoteAddress = ctx.channel().remoteAddress().toString();
        log.info("Remote Address: " + remoteAddress);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        String in = (String) msg;
        log.info("received data: " + in);

        MsgReq msgReq = new MsgReq(1l, 1l, "지금 헨들러에서 만든 메세지.");

        redisPub.publish("1", msgReq);

        msgService.onMsg(in);

        // 버퍼에 데이터를 쓰고 클라이언트에게 전송
//        ByteBuf responseBuf = ctx.alloc().buffer(in.length());
        buff.writeBytes(in.getBytes());
        final ChannelFuture f = ctx.writeAndFlush(buff.copy());
        f.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        ctx.close();
        cause.printStackTrace();
    }

}
