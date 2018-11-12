package com.coswafe.odyssey;

import com.coswafe.odyssey.constants.AppConstants;
import com.paygate.ag.common.utils.PayGateCryptoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.coswafe.odyssey.property.FileStorageProperties;

@SpringBootApplication
@ComponentScan(basePackages = { "com.coswafe.odyssey" })
@EnableConfigurationProperties({ FileStorageProperties.class })
public class FileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
    }


}
