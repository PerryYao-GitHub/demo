package com.ypy.backend.service;

import com.ypy.backend.common.Resp;

public interface AdminService {
    Resp switchUserStatus(Integer userId);
}
