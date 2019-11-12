package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_role")
public class Role extends AbstractEntity {

    @Column(name = "c_name", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '角色名称'")
    private String name;

    @Column(name = "c_parent_id", columnDefinition = "CHAR(32) COMMENT '父角色ID'")
    private String parentId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",
            joinColumns = @JoinColumn(name = "c_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "c_permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_menu_permission",
            joinColumns = @JoinColumn(name = "c_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "c_permission_menu_id", referencedColumnName = "id"))
    private Set<MenuPermission> menuPermissions;
}
