package com.wuaiyy.mapper;

import com.wuaiyy.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity com.wauiyy.domain.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select t1.perms from sys_menu t1 join sys_role_menu t2 on t1.menu_id = t2.menu_id join sys_user_role t3 on t3.role_id = t2.role_id where t3.user_id = #{userId} and t1.type = 2")
    List<String> getAllPermissions(Long userId);
}




