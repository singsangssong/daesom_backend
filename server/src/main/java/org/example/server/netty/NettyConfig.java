package org.example.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@RequiredArgsConstructor
public class NettyConfig {

    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private int port;
    @Value("${server.netty.boss-count}")
    private int bossCount;
    @Value("${server.netty.worker-count}")
    private int workerCount;
    @Value("${server.netty.keep-alive}")
    private boolean keepAlive;
    @Value("${server.netty.backlog}")
    private int backlog;

    @Bean
    public ServerBootstrap serverBootstrap(NettyChannelInitializer nettyChannelInitializer) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup(), workGroup())
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(nettyChannelInitializer);

        bootstrap.option(ChannelOption.SO_BACKLOG, backlog);

        return bootstrap;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workGroup() {
        return new NioEventLoopGroup(workerCount);
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    @Bean
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(host, port);
    }
}
