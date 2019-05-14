package com.liwj.szpd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.liwj.szpd.mapper")
public class SzpdApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SzpdApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(SzpdApplication.class);
    }
}
