package com.liaoin.muti.test.security.config.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @program: security_demo
 * @description: userDetails
 * @author: JingDe Ran
 * @create: 2019-10-25 10:47
 * @Email: sarakarma49@gmail.com
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆成功:{}",username);
        return UserInfo.builder().username(username).password("123456").build();
    }
}