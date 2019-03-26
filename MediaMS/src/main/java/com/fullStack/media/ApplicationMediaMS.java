package com.fullStack.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.fullStack.media.properties.FileStorageProperties;

@SpringBootApplication(scanBasePackages={"com.fullStack"})
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableDiscoveryClient
public class ApplicationMediaMS {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMediaMS.class, args);
	}

}
