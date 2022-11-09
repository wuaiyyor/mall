package com.wuaiyy.service;

import com.wuaiyy.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {

    List<String> getAllPermissions(Long userId);
}
