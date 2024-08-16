package org.example.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.chatroom.ChatRoomService;
import org.example.database.chatmsg.dto.MsgDto;
import org.example.database.redis.RedisPub;
import org.example.database.redis.MsgService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@io.netty.channel.ChannelHandler.Sharable
@RequiredArgsConstructor
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {

    private int DATA_LENGTH = 1024;
    private ByteBuf buff;

    private final MsgService msgService;
    private final ChatRoomService chatRoomService;
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
        MsgDto message = (MsgDto) msg;
        log.info("received data: " + message);

        chatRoomService.enterChatRoom(message.getRoomId().toString());

        redisPub.publish(chatRoomService.getTopic(message.getRoomId().toString()), message);
        msgService.saveMsg(message);

        // 버퍼에 데이터를 쓰고 클라이언트에게 전송
//        ByteBuf responseBuf = ctx.alloc().buffer(in.length());
        buff.writeBytes(message.toString().getBytes());
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
