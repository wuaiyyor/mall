package com.wuaiyy.domain;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 系统用户
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable, UserDetails {
    /**
     * 
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 创建者ID
     */
    @TableField(value = "create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 用户所在的商城Id
     */
    @TableField(value = "shop_id")
    private Long shopId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<String> permissions = new LinkedList<>();

    @Override
    public String getUsername() {
        return String.valueOf(this.userId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<>();
        if (CollUtil.isNotEmpty(permissions)) {
            permissions.forEach(permission->{
                if (permission.contains(",")) {
                    String[] pers = permission.split(",");
                    for (String per : pers) {
                        list.add(new SimpleGrantedAuthority(per));
                    }
                } else {
                    list.add(new SimpleGrantedAuthority(permission));
                }
            });
        }
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status==1;
    }

    @Override
    public boolean isEnabled() {
        return status==1;
    }
}