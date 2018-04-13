package cn.net.sunet.sunetcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("cn.net.sunet.sunetcloud.dao")
public class SunetcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunetcloudApplication.class, args);
	}
}
