package com.example.netty.netty;

import com.example.netty.properties.NettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : wxy
 * @Date : 2020/12/26 11:46
 */
@Slf4j
@Service
public class NettyServer {
    @Resource
    private NettyProperties nettyProperties;

    public void run() {
        log.info("===============Netty服务端启动===============");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new NettyInitChannel());
            Channel channel = bootstrap.bind(nettyProperties.getPort()).sync().channel();
            log.info("Netty服务端启动成功：" + channel.toString());
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("Netty服务端运行异常：" + e.getMessage());
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            log.info("Netty服务端已关闭");
        }
    }

}
