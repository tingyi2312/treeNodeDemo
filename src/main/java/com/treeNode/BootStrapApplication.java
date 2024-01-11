package com.treeNode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.treeNode.pojo")
@EnableTransactionManagement
@SpringBootApplication

public class BootStrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }
}