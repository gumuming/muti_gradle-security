package com.liaoin.muti.test.service.user.impl;

import com.liaoin.muti.test.file.util.java8.OptionalExt;
import com.liaoin.muti.test.security.UserInfo;
import com.liaoin.muti.test.security.UserInfoRepo;
import com.liaoin.muti.test.service.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author: RanJingde
 * program: test
 * description: 用户模块
 * create: 2019-11-12 14:11
 * Email: sarakarma49@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserInfoRepo userInfoRepo;
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OptionalExt<UserInfo> register(String username, String password) {
        UserInfo build = UserInfo.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
        userInfoRepo.save(build);
        return OptionalExt.ofNullable(build);
    }

    @Override
    public Optional<UserInfo> findById(Integer id) {
        return userInfoRepo.findById(id);
    }
}