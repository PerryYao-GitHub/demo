package com.ypy.backend.service;


import com.ypy.backend.common.Resp;
import com.ypy.backend.dto.AccountPasswordDTO;
import com.ypy.backend.dto.UserDTO;

/**
* @author 14870
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-09-17 22:11:05
*/
public interface UserAccountService {
    Resp register(AccountPasswordDTO dto);
    Resp login(AccountPasswordDTO dto);
    Resp changePassword(Integer id, AccountPasswordDTO dto);
    Resp delAccount(Integer id, AccountPasswordDTO dto);

    Resp checkAccount(Integer id);
    Resp editAccount(Integer id, UserDTO dto);
}
