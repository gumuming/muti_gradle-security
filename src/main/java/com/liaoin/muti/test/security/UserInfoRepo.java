package com.liaoin.muti.test.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo,Integer> {

    UserInfo findUserInfoByUsername(String username);
}
