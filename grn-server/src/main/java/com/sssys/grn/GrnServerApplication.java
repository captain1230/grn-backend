package com.sssys.grn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: captain
 * @Date: 11:12 2025/12/18
 */
@SpringBootApplication(scanBasePackages = "com.sssys.grn",
        exclude = {SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class})

public class GrnServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrnServerApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  grn-server启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n"
        );
    }
}
