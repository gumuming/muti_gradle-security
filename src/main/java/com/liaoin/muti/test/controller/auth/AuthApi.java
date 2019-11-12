package com.liaoin.muti.test.controller.auth;



import com.liaoin.muti.test.client.common.IdParam;
import com.liaoin.muti.test.client.auth.dto.request.RoleEditParam;
import com.liaoin.muti.test.client.auth.dto.request.UserRoleEditParam;
import com.liaoin.muti.test.client.auth.dto.response.*;
import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.service.auth.AuthService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
@ApiModel("菜单权限")
public class AuthApi {

    @Resource
    private AuthService authService;


    /**
     * 根据用户ID获取角色，包括角色信息、菜单列表、接口权限列表、路由权限列表
     * @param param 用户ID
     * @return 角色信息
     */
    @ApiOperation(value = "根据用户ID获取角色，包括角色信息、菜单列表、接口权限列表、路由权限列表",response = RoleDto.class)
    @PostMapping("/user/role")
    public Response<RoleDto> getRoleByUserId(@RequestBody IdParam param){
        return Response.success(authService.getRoleByUserId(param.getId()));
    }

    /**
     * 获取所有菜单权限树
     * @return 菜单权限树
     */
    @ApiOperation(value = "获取所有菜单权限树",response = MenuPermissionDto.class)
    @PostMapping("/menu/permission/tree")
    public Response<List<MenuPermissionDto>> getMenuPermissionsTree(){
        return Response.success(authService.getMenuPermissionsTree());
    }

    /**
     * 获取所有菜单权限
     * @return 菜单权限
     */
    @ApiOperation(value = "获取所有菜单权限",response =MenuPermissionDto.class )
    @PostMapping("/menu/permission")
    public Response<List<MenuPermissionDto>> getMenuPermissions(){
        return Response.success(authService.getMenuPermissions());
    }

    /**
     * 增加、修改菜单权限
     */
    @ApiOperation(value = "增加、修改菜单权限",response = MenuPermissionDto.class)
    @PostMapping("/menu/permission/edit")
    public Response<MenuPermissionDto> editMenuPermission(@RequestBody MenuPermissionDto param){
        return Response.success(authService.editMenuPermission(param));
    }

    /**
     * 删除菜单权限
     */
    @ApiOperation("删除菜单权限")
    @PostMapping("/menu/permission/del")
    public Response delMenuPermission(@RequestBody IdParam param){
        authService.delMenuPermission(param.getId());
        return Response.success();
    }

    /**
     * 获取所有非菜单权限树
     * @return 非菜单权限树
     */
    @ApiOperation(value = "获取所有非菜单权限树",response = PermissionDto.class)
    @PostMapping("/permission")
    public Response<List<PermissionDto>> getPermissions(){
        return Response.success(authService.getPermissions());
    }

    /**
     * 获取所有角色
     */
    @ApiOperation(value = "获取所有角色",response = RoleListDto.class)
    @PostMapping("/role/list")
    public Response<List<RoleListDto>> allRoles(){
        return Response.success(authService.getAllRoles());
    }

    /**
     * 根据用户id获取用户角色
     */
    @ApiOperation(value = "根据用户id获取用户角色",response = RoleListDto.class)
    @PostMapping("/user/role/list")
    public Response<List<RoleListDto>> userRoles(@RequestBody IdParam param){
        return Response.success(authService.userRoles(param.getId()));
    }

    /**
     * 修改角色权限
     * @param param 角色权限
     */
    @ApiOperation("修改角色权限")
    @PostMapping("/role/permission/edit")
    public Response editRolePermissions(@RequestBody RoleEditParam param){
        authService.editRolePermissions(param);
        return Response.success();
    }

    /**
     * 获取角色的菜单权限 的 ids
     */
    @ApiOperation(value = "获取角色的菜单权限ids",response = List.class)
    @PostMapping("/role/permission/menu/list")
    public Response<List<String>> roleMenuPermissionList(@RequestBody IdParam param){
        return Response.success(authService.roleMenuPermissionList(param.getId()));
    }

    /**
     * 删除角色
     * @param param 角色id
     */
    @ApiOperation("删除角色")
    @PostMapping("/role/permission/del")
    public Response delRolePermissions(@RequestBody IdParam param){
        authService.delRolePermissions(param.getId());
        return Response.success();
    }

    /**
     * 修改用户角色
     * @param param 用户及角色信息
     */
    @ApiOperation("修改用户角色")
    @PostMapping("/user/role/edit")
    public Response editUserRole(@RequestBody UserRoleEditParam param){
        authService.editUserRole(param);
        return Response.success();
    }

    /**
     * 删除用户角色
     * @param param 用户及角色信息
     */
    @ApiOperation(" 删除用户角色")
    @PostMapping("/user/role/del")
    public Response delUserRole(@RequestBody List<IdParam> param){
        authService.delUserRole(param);
        return Response.success();
    }
}
