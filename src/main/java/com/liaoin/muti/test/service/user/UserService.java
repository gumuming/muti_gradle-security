package com.liaoin.muti.test.service.user;

import com.liaoin.muti.test.file.util.java8.OptionalExt;
import com.liaoin.muti.test.security.UserInfo;

public interface UserService {
    OptionalExt<UserInfo> register(String username, String password);
}
