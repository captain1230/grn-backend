package com.sssys.grn.controller.demo;

import com.sssys.grn.system.entity.SysUser;
import com.sssys.grn.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: captain
 * @Date: 11:01 2025/12/18
 */
@RestController // 等价于 @Controller + @ResponseBody，直接返回JSON/字符串
@RequestMapping("/hello") // 统一前缀，区分模块（system模块）
public class HelloController {

    @Autowired
    private SysUserServiceImpl systemUserService;
    @GetMapping("/world")
    public String helloWorld() {
        List<SysUser> list = systemUserService.list();
        return "Hello, grn-system! 🚀" + list.size();
    }


}