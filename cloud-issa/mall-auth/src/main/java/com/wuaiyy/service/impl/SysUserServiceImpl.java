package com.wuaiyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuaiyy.domain.SysUser;
import com.wuaiyy.service.SysUserService;
import com.wuaiyy.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<String> getAllPermissions(Long userId) {
        return sysUserMapper.getAllPermissions(userId);
    }
}




