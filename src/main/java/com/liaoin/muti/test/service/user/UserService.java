package com.liaoin.muti.test.service.user;

import com.liaoin.muti.test.file.util.java8.OptionalExt;
import com.liaoin.muti.test.security.UserInfo;

import java.util.Optional;


public interface UserService {
    /**
     * 注册
     * @param username username
     * @param password password
     * @return   OptionalExt<UserInfo>
     */
    OptionalExt<UserInfo> register(String username, String password);

    /**
     * 通过 Id 查询
     * @param id id
     * @return  OptionalExt<UserInfo>
     */
    Optional<UserInfo> findById(Integer id);
}
