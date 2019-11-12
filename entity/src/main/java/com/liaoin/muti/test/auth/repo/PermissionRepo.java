package com.liaoin.muti.test.auth.repo;

import com.liaoin.muti.test.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, String> {

}
