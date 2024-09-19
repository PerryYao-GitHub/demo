package com.ypy.backend.controller;

import cn.hutool.core.util.StrUtil;
import com.ypy.backend.common.Code;
import com.ypy.backend.common.Resp;
import com.ypy.backend.common.UserRole;
import com.ypy.backend.common.exception.BusinessException;
import com.ypy.backend.dto.AccountPasswordDTO;
import com.ypy.backend.service.AdminService;
import com.ypy.backend.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    // 管理员登录
    @PostMapping("/login")
    public Resp login(@RequestBody AccountPasswordDTO dto) {
        String account = dto.getAccount();
        String password = dto.getPassword();
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password)) throw new BusinessException(Code.ERROR_PARAMS_NULL);
        if (!(account.equals("admin") && password.equals("123456"))) throw new BusinessException(Code.ERROR_PARAMS_INVALID, "wrong username or password");
        String token = JwtUtils.genToken(0, UserRole.ADMIN.getCode());
        return Resp.success(token);
    }

    // 禁评某用户
    @GetMapping("/user/switch_status/{id}")
    public Resp switchUserStatus(@PathVariable Integer id) {
        if (id == null || id <= 0) return Resp.error(Code.ERROR_PARAMS_INVALID);
        return adminService.switchUserStatus(id);
    }
}
