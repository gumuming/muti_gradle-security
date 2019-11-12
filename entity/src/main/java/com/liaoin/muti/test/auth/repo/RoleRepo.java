package com.liaoin.muti.test.auth.repo;

import com.liaoin.muti.test.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
}
