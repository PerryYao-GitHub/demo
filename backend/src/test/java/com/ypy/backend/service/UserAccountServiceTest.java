package com.ypy.backend.service;

import com.ypy.backend.common.Description;
import com.ypy.backend.common.exception.BusinessException;
import com.ypy.backend.dto.AccountPasswordDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountServiceTest {
    @Resource
    private UserAccountService userAccountService;

    @Test
    void register() {
        AccountPasswordDTO dto = new AccountPasswordDTO();

        // 测试注册成功的场景
        dto.setAccount("user01");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        userAccountService.register(dto);

        // 测试账号包含非法字符
        dto.setAccount("user@#02");
        dto.setPassword("password123");
        dto.setConfirmPassword("password123");
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userAccountService.register(dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_FORMAT.getStr(), exception.getDescription());

        // 测试密码和确认密码不一致
        dto.setAccount("user03");
        dto.setPassword("password123");
        dto.setConfirmPassword("password321");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.register(dto);
        });
        assertEquals(Description.WRONG_CONFIRM_PASSWORD.getStr(), exception.getDescription());

        // 测试重复注册相同账号
        dto.setAccount("user01");
        dto.setPassword("password123");
        dto.setConfirmPassword("password123");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.register(dto);
        });
        assertEquals(Description.ACCOUNT_EXISTS.getStr(), exception.getDescription());

        // 测试账号、密码为空的情况
        dto.setAccount("");
        dto.setPassword("password123");
        dto.setConfirmPassword("password123");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.register(dto);
        });
        assertEquals(Description.EMPTY_ERROR.getStr(), exception.getDescription());

        // 再注册几条假数据
        dto.setAccount("user02");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        userAccountService.register(dto);

        dto.setAccount("user03");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        userAccountService.register(dto);

        dto.setAccount("user04");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        userAccountService.register(dto);

        dto.setAccount("user05");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        userAccountService.register(dto);
    }

    @Test
    void login() {
        AccountPasswordDTO dto = new AccountPasswordDTO();

        // 模拟登陆成功
        dto.setAccount("user01");
        dto.setPassword("123456");
        userAccountService.login(dto);

        // 模拟密码错误
        dto.setAccount("user02");
        dto.setPassword("password123");
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userAccountService.login(dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_PASSWORD.getStr(), exception.getDescription());

        // 模拟账户错误
        dto.setAccount("user022");
        dto.setPassword("password123");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.login(dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_PASSWORD.getStr(), exception.getDescription());
    }

    @Test
    void changePassword() {
        AccountPasswordDTO dto = new AccountPasswordDTO();

        // 模拟成功修改密码
        dto.setAccount("user05");
        dto.setPassword("123456");
        dto.setNewPassword("1234");
        dto.setConfirmPassword("1234");
        userAccountService.changePassword(5, dto);

        // 模拟二次输入错误
        dto.setAccount("user04");
        dto.setPassword("123456");
        dto.setNewPassword("1234");
        dto.setConfirmPassword("123456");
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userAccountService.changePassword(4, dto);
        });
        assertEquals(Description.WRONG_CONFIRM_PASSWORD.getStr(), exception.getDescription());

        // 模拟输错密码
        dto.setAccount("user04");
        dto.setPassword("123");
        dto.setNewPassword("1234");
        dto.setConfirmPassword("1234");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.changePassword(4, dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_PASSWORD.getStr(), exception.getDescription());

        // 模拟不是当前用户
        dto.setAccount("user04");
        dto.setPassword("123456");
        dto.setNewPassword("1234");
        dto.setConfirmPassword("1234");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.changePassword(5, dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_PASSWORD.getStr(), exception.getDescription());
    }

    @Test
    void delAccount() {
        AccountPasswordDTO dto = new AccountPasswordDTO();

        // 模拟成功删除
        dto.setAccount("user05");
        dto.setPassword("1234");
        userAccountService.delAccount(5, dto);

        // 模拟输入密码错误
        dto.setAccount("user04");
        dto.setPassword("1234");
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userAccountService.delAccount(4, dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_PASSWORD.getStr(), exception.getDescription());

        // 模拟非法用户
        dto.setAccount("user04");
        dto.setPassword("123456");
        exception = assertThrows(BusinessException.class, () -> {
            userAccountService.delAccount(5, dto);
        });
        assertEquals(Description.WRONG_ACCOUNT_PASSWORD.getStr(), exception.getDescription());
    }

    @Test
    void checkAccount() {
    }

    @Test
    void editAccount() {
    }
}
