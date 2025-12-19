package com.sssys.grn.controller.system;


import com.sssys.grn.common.exception.BusinessException;
import com.sssys.grn.common.pojo.CommonResult;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常测试控制器（专门用于抛出各类异常）
 */
@RestController
@RequestMapping("/api/test/exception")
public class TestExceptionController {


    // ========== 2. 自定义业务异常测试 ==========
    @GetMapping("/business")
    public CommonResult<?> businessExceptionTest(@RequestParam String msg) {
        throw new BusinessException(msg, 400);
    }

    // ========== 4. 权限不足异常测试 ==========
    @GetMapping("/forbidden")
    @PreAuthorize("hasRole('SUPER_ADMIN')") // 模拟无权限的角色
    public CommonResult<?> forbiddenTest() {
        return CommonResult.success("权限校验通过");
    }

    // ========== 5. 运行时异常测试 ==========
    @GetMapping("/runtime")
    public CommonResult<?> runtimeExceptionTest() {
        throw new RuntimeException("模拟运行时异常");
    }
}
