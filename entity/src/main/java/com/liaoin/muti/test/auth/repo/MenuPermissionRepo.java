package com.liaoin.muti.test.auth.repo;



import com.liaoin.muti.test.auth.entity.MenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuPermissionRepo extends JpaRepository<MenuPermission, String> {
    List<MenuPermission> findByParentId(String id);
}
