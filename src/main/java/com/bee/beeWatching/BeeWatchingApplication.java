package com.bee.beeWatching;

import com.bee.beeWatching.Config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableJpaRepositories(basePackages = "com.bee.beeWatching.Repository")
public class BeeWatchingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeeWatchingApplication.class, args);
	}

}
