package com.butterfly.game;

import com.butterfly.game.netty.server.TimeServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(GameApplication.class, args);
		TimeServer server = context.getBean(TimeServer.class);
		server.start(8888);
	}

}
