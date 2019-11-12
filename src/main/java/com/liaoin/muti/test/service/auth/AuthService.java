package com.liaoin.muti.test.service.auth;




import com.liaoin.muti.test.client.common.IdParam;
import com.liaoin.muti.test.client.auth.dto.request.RoleEditParam;
import com.liaoin.muti.test.client.auth.dto.request.UserRoleEditParam;
import com.liaoin.muti.test.client.auth.dto.response.*;

import java.util.List;

public interface AuthService {



    /**
     * 根据用户ID获取角色，包括角色信息、菜单列表、接口权限列表、路由权限列表
     * @param userId 用户ID
     * @return 角色信息
     */
    RoleDto getRoleByUserId(String userId);

    /**
     * 获取所有菜单权限树
     * @return 菜单权限树
     */
    List<MenuPermissionDto> getMenuPermissionsTree();

    /**
     * 获取所有菜单权限
     * @return 菜单权限
     */
    List<MenuPermissionDto> getMenuPermissions();


    /**
     * 获取所有非菜单权限树
     * @return 非菜单权限树
     */
    List<PermissionDto> getPermissions();

    /**
     * 修改角色权限
     * @param param 角色权限
     */
    void editRolePermissions(RoleEditParam param);

    /**
     * 删除角色
     * @param id 角色id
     */
    void delRolePermissions(String id);

    /**
     * 修改用户角色
     * @param param 用户及角色信息
     */
    void editUserRole(UserRoleEditParam param);

    /**
     * 删除用户角色
     * @param params 用户及角色信息
     */
    void delUserRole(List<IdParam> params);

    /**
     * 增加、修改菜单权限
     */
    MenuPermissionDto editMenuPermission(MenuPermissionDto param);

    /**
     * 删除菜单权限
     */
    void delMenuPermission(String id);

    /**
     * 获取所有角色
     */
    List<RoleListDto> getAllRoles();

    /**
     * 根据用户id获取用户角色
     */
    List<RoleListDto> userRoles(String id);

    /**
     * 获取角色的菜单权限ids
     */
    List<String> roleMenuPermissionList(String id);
}
