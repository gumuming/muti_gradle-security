package com.liaoin.muti.test.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆校验名称:{}",username);
        return UserInfo.builder().username(username).password(bCryptPasswordEncoder.encode("123456")).build();
    }
}