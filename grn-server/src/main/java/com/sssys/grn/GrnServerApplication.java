package com.sssys.grn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: captain
 * @Date: 11:12 2025/12/18
 */
@SpringBootApplication(scanBasePackages = "com.sssys.grn")
public class GrnServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrnServerApplication.class, args);
        System.out.println("✅ grn-server 启动成功！");
    }
}
