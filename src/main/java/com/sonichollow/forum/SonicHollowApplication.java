package com.sonichollow.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@MapperScan(basePackages = "org.sang.mybatis.mapper")
public class SonicHollowApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SonicHollowApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SonicHollowApplication.class);
    }
}
