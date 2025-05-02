package com.example.flink.realtime.publisher;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.flink.realtime.publisher.mapper")
public class FlinkRealtimePublisherApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlinkRealtimePublisherApplication.class, args);
    }
}
