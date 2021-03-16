package com.example.auth.dto;

import com.example.common.user.UserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 登录用户信息
 *
 * @Author wxy
 * @Date 2021/2/8 11:05
 * @Version 1.0
 */
@Data
public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 6555321999509986913L;

    /**
     * ID
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;
    /**
     * 登录客户端ID
     */
    private String clientId;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {
    }

    public SecurityUser(UserDto userDto) {
        this.setId(userDto.getId());
        this.setUsername(userDto.getUsername());
        this.setPassword(userDto.getPassword());
        this.setEnabled(userDto.getStatus());
        this.setClientId(userDto.getClientId());
        this.setAuthorities(userDto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
