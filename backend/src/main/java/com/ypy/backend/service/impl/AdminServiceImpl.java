package com.ypy.backend.service.impl;


import com.ypy.backend.common.Code;
import com.ypy.backend.common.Resp;
import com.ypy.backend.common.UserStatus;
import com.ypy.backend.common.exception.BusinessException;
import com.ypy.backend.entity.User;
import com.ypy.backend.mapper.UserMapper;
import com.ypy.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Resp switchUserStatus(Integer id) {
        User user = userMapper.selectById(id);
        if (user == null) throw new BusinessException(Code.ERROR_PARAMS_INVALID, "no such user");
        if (Objects.equals(user.getStatus(), UserStatus.AVAILABLE.getCode())) user.setStatus(UserStatus.BANNED.getCode());
        else if (Objects.equals(user.getStatus(), UserStatus.BANNED.getCode())) user.setStatus(UserStatus.AVAILABLE.getCode());
        userMapper.updateById(user);
        return Resp.success();
    }
}
