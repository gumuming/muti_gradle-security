package com.liaoin.muti.test.service.auth.impl;


import com.liaoin.muti.test.auth.entity.*;
import com.liaoin.muti.test.auth.repo.*;
import com.liaoin.muti.test.client.common.IdParam;
import com.liaoin.muti.test.client.auth.dto.request.RoleEditParam;
import com.liaoin.muti.test.client.auth.dto.request.UserRoleEditParam;
import com.liaoin.muti.test.client.auth.dto.response.*;
import com.liaoin.muti.test.service.auth.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private RoleRepo roleRepo;

    @Resource
    private UserRepo userRepo;

    @Resource
    private PermissionRepo permissionRepo;

    @Resource
    private MenuPermissionRepo menuPermissionRepo;

    @Resource
    private MenuPermissionMetaRepo menuPermissionMetaRepo;



    /**
     * 根据用户ID获取角色，包括角色信息、菜单列表、接口权限列表、路由权限列表
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    @Override
    public RoleDto getRoleByUserId(String userId) {
        User user = userRepo.findByUserId(userId).orElse(null);
        if (null == user) {
            return null;
        }


        RoleDto roleDto = new RoleDto();
        Set<String> names = new HashSet<>();
        Set<MenuPermission> menuPermissions = new HashSet<>();
        Set<String> apis = new HashSet<>();
        Set<String> routes = new HashSet<>();

        user.getRoles().forEach(role -> {
            names.add(role.getName());
            menuPermissions.addAll(role.getMenuPermissions());
            role.getPermissions().forEach(permission -> {
                routes.add(permission.getUrl());
                permission.getApis().forEach(permissionApi -> {
                    apis.add(permissionApi.getUrl());
                });
            });
        });

        List<MenuPermissionDto> menuPermissionDtoList = menuPermissionToDto(menuPermissionToTree(menuPermissions));
        Collections.reverse(menuPermissionDtoList);
        roleDto.setMenus(menuPermissionDtoList);
        roleDto.setNames(new ArrayList<>(names));
        roleDto.setApis(new ArrayList<>(apis));
        roleDto.setRoutes(new ArrayList<>(routes));

        return roleDto;
    }

    /**
     * 获取所有菜单权限树
     *
     * @return 菜单权限树
     */
    @Override
    public List<MenuPermissionDto> getMenuPermissionsTree() {
        List<MenuPermission> menuPermissionList = menuPermissionRepo.findAll();
        return menuPermissionToDto(menuPermissionToTree(new HashSet<>(menuPermissionList)));
    }

    /**
     * 获取所有菜单权限
     *
     * @return 菜单权限
     */
    @Override
    public List<MenuPermissionDto> getMenuPermissions() {
        List<MenuPermission> menuPermissionList = menuPermissionRepo.findAll();
        // 定义前端需要的最上层父级
        MenuPermissionDto menuPermissionParent = new MenuPermissionDto();
        menuPermissionParent.setId("-1");
        menuPermissionParent.setName("全部");
        menuPermissionParent.setPath("/");
        MenuPermissionMetaDto menuPermissionMeta = new MenuPermissionMetaDto();
        menuPermissionMeta.setTitle("全部");
        menuPermissionParent.setMeta(menuPermissionMeta);


        List<MenuPermissionDto> dtos = menuPermissionToDto(menuPermissionToTree(new HashSet<>(menuPermissionList)));

        Collections.reverse(dtos);

        List<MenuPermissionDto> result = new ArrayList<>();

        result.add(menuPermissionParent);

        permissionTreeToList(dtos,result);

        return result;
    }

    /**
     * 获取所有非菜单权限树
     *
     * @return 非菜单权限树
     */
    @Override
    public List<PermissionDto> getPermissions() {
        List<Permission> permissions = permissionRepo.findAll();
        return permissionToDto(permissionToTree(permissions));
    }

    /**
     * 修改角色权限
     *
     * @param param 角色权限
     */
    @Override
    public void editRolePermissions(RoleEditParam param) {
        Role role;
        if (StringUtils.isEmpty(param.getId())) {
            role = new Role();
        } else {
            role = roleRepo.findById(param.getId()).orElseThrow(() -> new RuntimeException("数据不存在！"));
        }

        if (!StringUtils.isEmpty(param.getName())) {
            role.setName(param.getName());
        }

        if (null != param.getMenuPermissionIds()) {
            role.setMenuPermissions(null);
            roleRepo.save(role);
            if (param.getMenuPermissionIds().size() > 0) {
                role.setMenuPermissions(new HashSet<>());
                param.getMenuPermissionIds().forEach(s -> {
                    MenuPermission permission = new MenuPermission();
                    permission.setId(s);
                    role.getMenuPermissions().add(permission);
                });
            }
        }

        if (null != param.getPermissionIds()) {
            role.setPermissions(null);
            if (param.getPermissionIds().size() > 0) {
                role.setPermissions(new HashSet<>());
                param.getPermissionIds().forEach(s -> {
                    Permission permission = new Permission();
                    permission.setId(s);
                    role.getPermissions().add(permission);
                });
            }
        }

        try {
            roleRepo.save(role);
        } catch (Exception e) {
            throw new RuntimeException("数据有误！");
        }
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    @Override
    public void delRolePermissions(String id) {
        roleRepo.deleteById(id);
    }

    /**
     * 修改用户角色
     *
     * @param param 用户及角色信息
     */
    @Override
    @Transactional
    public void editUserRole(UserRoleEditParam param) {
        if (param.getUserIds().size() == 0 || null == param.getRoleIds() || param.getRoleIds().size() == 0) {
            throw new RuntimeException("缺少参数！");
        }
        param.getUserIds().forEach(id -> {
            User user = userRepo.findByUserId(id).orElse(new User(id, new HashSet<>()));
            user.setRoles(new HashSet<>());
            userRepo.save(user);
            param.getRoleIds().forEach(s -> {
                Role role = new Role();
                role.setId(s);
                user.getRoles().add(role);
            });
            userRepo.save(user);
        });
    }

    /**
     * 删除用户角色
     *
     * @param params 用户及角色信息
     */
    @Override
    @Transactional
    public void delUserRole(List<IdParam> params) {
        params.forEach(param -> {
            userRepo.findByUserId(param.getId()).ifPresent(user -> {
                user.setRoles(null);
                userRepo.save(user);
                userRepo.delete(user);
            });
        });
    }

    /**
     * 增加、修改菜单权限
     */
    @Override
    @Transactional
    public MenuPermissionDto editMenuPermission(MenuPermissionDto param) {
        // 存储meta信息
        MenuPermissionMeta meta;
        if(StringUtils.isEmpty(param.getMeta().getId())){
            meta = new MenuPermissionMeta();
        }else{
            meta = menuPermissionMetaRepo.findById(param.getMeta().getId()).orElseThrow(() -> new RuntimeException("数据不存在!"));
        }
        BeanUtils.copyProperties(param.getMeta(),meta,"id");
        menuPermissionMetaRepo.save(meta);
        param.getMeta().setId(meta.getId());


        // 存储permission信息
        MenuPermission menuPermission;
        if(StringUtils.isEmpty(param.getId())){
            menuPermission = new MenuPermission();
        }else{
            menuPermission = menuPermissionRepo.findById(param.getId()).orElseThrow(() -> new RuntimeException("数据不存在!"));
        }
        BeanUtils.copyProperties(param,menuPermission,"id","children","meta");
        if(param.getParentId().equals("-1")){
            menuPermission.setParentId(null);
        }
        menuPermission.setMeta(meta);
        menuPermissionRepo.save(menuPermission);
        param.setId(menuPermission.getId());
        return param;
    }

    /**
     * 删除菜单权限
     */
    @Override
    @Transactional
    public void delMenuPermission(String id) {
        MenuPermission menuPermission = menuPermissionRepo.findById(id).orElseThrow(() -> new RuntimeException("数据不存在"));
        menuPermissionMetaRepo.delete(menuPermission.getMeta());
        menuPermission.setMeta(null);
        menuPermissionRepo.delete(menuPermission);
        List<MenuPermission> menuPermissionChildren = menuPermissionRepo.findByParentId(id);
        menuPermissionChildren.forEach(menuPermissionChild -> {
            menuPermissionMetaRepo.delete(menuPermissionChild.getMeta());
            menuPermissionChild.setMeta(null);
            menuPermissionRepo.delete(menuPermissionChild);
        });
    }

    /**
     * 获取所有角色
     */
    @Override
    public List<RoleListDto> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream().map(role -> new RoleListDto(role.getId(),role.getName(),role.getParentId())).collect(Collectors.toList());
    }

    /**
     * 根据用户id获取用户角色
     */
    @Override
    public List<RoleListDto> userRoles(String id) {
        if(StringUtils.isEmpty(id)){
            throw new RuntimeException("参数错误！");
        }
        List<RoleListDto> roleListDtoList = new ArrayList<>();
        userRepo.findByUserId(id).ifPresent(user -> {
            user.getRoles().forEach(role -> {
                roleListDtoList.add(new RoleListDto(role.getId(),role.getName(),role.getParentId()));

            });
        });
        return roleListDtoList;
    }

    /**
     * 获取角色的菜单权限ids
     */
    @Override
    public List<String> roleMenuPermissionList(String id) {
        List<String> ids = new ArrayList<>();
        roleRepo.findById(id).ifPresent(role -> {
            role.getMenuPermissions().forEach(menuPermission -> {
                ids.add(menuPermission.getId());
            });
        });
        return ids;
    }

    /**
     * 递归将树形菜单权限树转为非树，用于后台管理
     */
    private void permissionTreeToList(List<MenuPermissionDto> dtos, List<MenuPermissionDto> result){
        dtos.forEach(menuPermissionDto -> {
            if(StringUtils.isEmpty(menuPermissionDto.getParentId())){
                menuPermissionDto.setParentId("-1");
            }
            result.add(menuPermissionDto);
            if(null != menuPermissionDto.getChildren() && menuPermissionDto.getChildren().size()>0){
                Collections.reverse(menuPermissionDto.getChildren());
                permissionTreeToList(menuPermissionDto.getChildren(),result);
            }
        });
    }

    /**
     * 将数据库非菜单权限实体转换为树
     *
     * @param permissions 数据库实体
     * @return 树形dto
     */
    private List<Permission> permissionToTree(List<Permission> permissions) {
        List<Permission> permissionTrees = new ArrayList<>();
        for (Permission permission : permissions) {
            //找到根
            if (StringUtils.isEmpty(permission.getParentId())) {
                permissionTrees.add(permission);
            }
            //找到子
            for (Permission permissionChild : permissions) {
                if (permission.getId().equals(permissionChild.getParentId())) {
                    if (permission.getChildren() == null) {
                        permission.setChildren(new ArrayList<>());
                    }
                    permission.getChildren().add(permissionChild);
                }
            }
        }

        return permissionTrees;
    }

    /**
     * 递归将非菜单树形实体转换为dto
     *
     * @param permissionTrees 树形实体
     * @return dto
     */
    private List<PermissionDto> permissionToDto(List<Permission> permissionTrees) {
        List<PermissionDto> permissionDtoList = new ArrayList<>();
        permissionTrees.forEach(permission -> {
            PermissionDto PermissionDto = new PermissionDto(permission.getId(), permission.getName(), permission.getUrl(), permission.getParentId(), null);
            permissionDtoList.add(PermissionDto);
            if (null != permission.getChildren() && permission.getChildren().size() > 0) {
                PermissionDto.setChildren(permissionToDto(permission.getChildren()));
            }
        });
        return permissionDtoList;
    }


    /**
     * 将数据库权限实体转换为树
     *
     * @param menuPermissionSet 数据库实体
     * @return 树形dto
     */
    private List<MenuPermission> menuPermissionToTree(Set<MenuPermission> menuPermissionSet) {
        List<MenuPermission> menuPermissions = new ArrayList<>(menuPermissionSet);
        Collections.sort(menuPermissions, (u1, u2) -> {
            if(null == u1.getOrder()){
                return -1;
            }
            if(null == u2.getOrder()){
                return 1;
            }
            int diff = u1.getOrder() - u2.getOrder();
            if (diff > 0) {
                return -1;
            } else if (diff < 0) {
                return 1;
            }
            return 0; //相等为0
        });
        List<MenuPermission> menuPermissionTrees = new ArrayList<>();
        for (MenuPermission menuPermission : menuPermissions) {
            //找到根
            if (StringUtils.isEmpty(menuPermission.getParentId())) {
                menuPermissionTrees.add(menuPermission);
            }
            //找到子
            for (MenuPermission PermissionChild : menuPermissions) {
                if (menuPermission.getId().equals(PermissionChild.getParentId())) {
                    if (menuPermission.getChildren() == null) {
                        menuPermission.setChildren(new ArrayList<>());
                    }
                    menuPermission.getChildren().add(PermissionChild);
                }
            }
        }

        return menuPermissionTrees;
    }

    /**
     * 递归将树形实体转换为dto
     *
     * @param menuPermissionTrees 树形实体
     * @return dto
     */
    private List<MenuPermissionDto> menuPermissionToDto(List<MenuPermission> menuPermissionTrees) {
        List<MenuPermissionDto> menuPermissionDtoList = new ArrayList<>();
        menuPermissionTrees.forEach(menuPermission -> {
            MenuPermissionDto menuPermissionDto = new MenuPermissionDto(menuPermission.getId(), menuPermission.getParentId(), menuPermission.getName(), menuPermission.getPath(), menuPermission.getComponent()
                    , menuPermission.getRedirect(), menuPermission.getAlwaysShow(), menuPermission.getLeaf(),menuPermission.getHidden(),menuPermission.getStatus(), menuPermission.getOrder()
                    , new MenuPermissionMetaDto(menuPermission.getMeta().getId(), menuPermission.getMeta().getTitle(), menuPermission.getMeta().getDefaultIcon(), menuPermission.getMeta().getActiveIcon()), null);
            menuPermissionDtoList.add(menuPermissionDto);
            if (null != menuPermission.getChildren() && menuPermission.getChildren().size() > 0) {
                menuPermissionDto.setChildren(menuPermissionToDto(menuPermission.getChildren()));
            }
        });
        return menuPermissionDtoList;
    }

    /**
     * 将菜单dto信息赋值给实体
     */
    private void setMenuPermissionInfo(MenuPermission menuPermission, MenuPermissionDto dto){
        menuPermission.setName(dto.getName());
        menuPermission.setPath(dto.getPath());
        menuPermission.setComponent(dto.getComponent());
        menuPermission.setRedirect(dto.getRedirect());
        menuPermission.setAlwaysShow(dto.getAlwaysShow());
        menuPermission.setLeaf(dto.getLeaf());
        menuPermission.setHidden(dto.getHidden());
        menuPermission.setOrder(dto.getOrder());
        menuPermission.setStatus(dto.getStatus());
    }

    /**
     * 将菜单metaDto信息赋值给实体
     */
    private void setMenupermissionMetaInfo(MenuPermissionMeta meta, MenuPermissionMetaDto dto){
        meta.setTitle(dto.getTitle());
        meta.setActiveIcon(dto.getActiveIcon());
        meta.setDefaultIcon(dto.getDefaultIcon());
    }

}
