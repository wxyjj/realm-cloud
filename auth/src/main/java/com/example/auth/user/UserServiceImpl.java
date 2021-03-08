package com.example.auth.user;

import com.example.auth.constant.MessageConstant;
import com.example.auth.dto.SecurityUser;
import com.example.auth.feign.UmsFeign;
import com.example.common.constant.AuthConstant;
import com.example.common.support.Result;
import com.example.common.user.UserDto;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理业务类
 *
 * @Author wxy
 * @Date 2021/2/8 11:05
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    @Resource
    private UmsFeign umsFeign;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserDto userDto = null;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            Result<UserDto> result = umsFeign.loadUserByUsername(username);
            if (result.getCode() != 200L) {
                throw new AuthenticationServiceException(MessageConstant.DEMOTION);
            }
            userDto = result.getData();
        }
        if (null == userDto) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

}
