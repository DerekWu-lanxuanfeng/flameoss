package com.flame.flameoss;

import com.flame.flameoss.global.InitServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlameOSSApplication {

	private static final Logger LOG = LoggerFactory.getLogger(FlameOSSApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlameOSSApplication.class, args);
		InitServer.init();
		LOG.info("FlameOSS starting ...");
	}

}
