package com.liaoin.muti.test.auth.repo;


import com.liaoin.muti.test.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByUserId(String id);

    void deleteByUserId(String id);
}
