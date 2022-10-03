package com.BTL;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.BTL.config.StorageProperties;
import com.BTL.service.StorageService;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlApplication.class, args);
	}
	
	/**
	 * gọi khởi tạo nơi lưu
	 * @param storageService
	 * @return
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}

}
