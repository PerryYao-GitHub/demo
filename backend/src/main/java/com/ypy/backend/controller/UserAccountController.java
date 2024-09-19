package com.ypy.backend.controller;

import com.ypy.backend.common.Resp;
import com.ypy.backend.dto.AccountPasswordDTO;
import com.ypy.backend.dto.UserDTO;
import com.ypy.backend.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserAccountController {
    @Resource
    private UserAccountService userAccountService;

    static Integer getUserIdFromRequest (HttpServletRequest request) {
        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
        return (Integer) claims.get("id");
    }

    // 以下操作不需要get id
    @PostMapping("/register")
    public Resp register(@RequestBody AccountPasswordDTO dto) {
        return userAccountService.register(dto);
    }

    @PostMapping("/login")
    public Resp login(@RequestBody AccountPasswordDTO dto) {
        return userAccountService.login(dto);
    }

    // 后面需要 get id
    @PostMapping("/change_password")
    public Resp changePassword(@RequestBody AccountPasswordDTO dto, HttpServletRequest request) {
        Integer id = getUserIdFromRequest(request);
        return userAccountService.changePassword(id, dto);
    }

    @PostMapping("/del_account")
    public Resp delAccount(@RequestBody AccountPasswordDTO dto, HttpServletRequest request) {
        Integer id = getUserIdFromRequest(request);
        return userAccountService.delAccount(id, dto);
    }

    @GetMapping("/check_account")
    public Resp checkAccount(HttpServletRequest request) {
        Integer id = getUserIdFromRequest(request);
        return userAccountService.checkAccount(id);
    }

    @PostMapping("/edit_account")
    public Resp editAccount(@RequestBody UserDTO dto, HttpServletRequest request) {
        Integer id = getUserIdFromRequest(request);
        return userAccountService.editAccount(id, dto);
    }
}
