package com.ypy.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String password;

    private String name;

    private String avatar;

    private String phone;

    private String email;

    private String tags;

    private Byte status;

    private Byte role;

    private LocalDateTime loginTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Byte deleted;

    private static final long serialVersionUID = 1L;
}
