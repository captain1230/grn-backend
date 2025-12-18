package com.sssys.grn.controller.demo;

import com.sssys.grn.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: captain
 * @Date: 11:01 2025/12/18
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private SysUserServiceImpl systemUserService;
    @GetMapping("/world")
    public String helloWorld() {
        int count = systemUserService.list().size();
        return "Hello, grn-system! 🚀" +count;
    }


}