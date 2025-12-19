package com.sssys.grn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.sssys.grn.system.entity.SysUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户接口（带OpenAPI注解）
 */
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "用户管理接口", description = "用户的增删改查接口")
public class UserController {

    /**
     * 根据ID查询用户
     */
    @Operation(summary = "查询单个用户", description = "根据用户ID查询用户详情")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SysUser> getUserById(
            @Parameter(name = "id", description = "用户ID", required = true, in = ParameterIn.PATH, example = "1001")
            @PathVariable Long id) {

        return ResponseEntity.ok(new SysUser());
    }
}
