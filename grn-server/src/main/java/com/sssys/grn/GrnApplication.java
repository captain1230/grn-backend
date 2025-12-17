package com.sssys.grn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:
 * @Author: captain
 * @Date: 2025/12/17
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class GrnApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrnApplication.class, args);
        System.out.println("""
                (♥◠‿◠)ﾉﾞ  GRN启动成功   ლ(´ڡ`ლ)ﾞ \s
                 .-------.       ____     __       \s
                 |  _ _   \\      \\   \\   /  /   \s
                 | ( ' )  |       \\  _. /  '      \s
                 |(_ o _) /        _( )_ .'        \s
                 | (_,_).' __  ___(_ o _)'         \s
                 |  |\\ \\  |  ||   |(_,_)'        \s
                 |  | \\ `'   /|   `-'  /          \s
                 |  |  \\    /  \\      /          \s
                 ''-'   `'-'    `-..-'             \s""");
    }
}
