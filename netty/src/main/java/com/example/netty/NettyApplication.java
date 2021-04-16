package com.example.netty;

import com.example.netty.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class NettyApplication {
	@Resource
	private NettyServer nettyServer;

	public static void main(String[] args) {
		SpringApplication.run(NettyApplication.class, args);
	}

	@PostConstruct
	public void init() {
		CompletableFuture.runAsync(() -> nettyServer.run());
	}

}
