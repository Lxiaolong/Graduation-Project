package cn.net.sunet.sunetcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@MapperScan("cn.net.sunet.sunetcloud.dao")
public class SunetcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunetcloudApplication.class, args);
	}
}
