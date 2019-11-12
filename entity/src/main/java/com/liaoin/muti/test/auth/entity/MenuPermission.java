package com.liaoin.muti.test.auth.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.liaoin.muti.test.base.AbstractEntity;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_permission_menu")
public class MenuPermission extends AbstractEntity {

    @Column(name = "c_name", columnDefinition = "VARCHAR(255) COMMENT '路由名称'")
    private String name;

    @Column(name = "c_path", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '路由路径'")
    private String path;

    @Column(name = "c_component", columnDefinition = "VARCHAR(255) NOT NULL")
    private String component;

    @Column(name = "c_redirect", columnDefinition = "VARCHAR(255)")
    private String redirect;

    @Column(name = "c_always_show", columnDefinition = "TINYINT(1)")
    private Boolean alwaysShow;

    @Column(name = "c_leaf", columnDefinition = "TINYINT(1)")
    private Boolean leaf;

    @Column(name = "c_hidden", columnDefinition = "TINYINT(1)")
    private Boolean hidden;

    @Column(name = "c_status", columnDefinition = "TINYINT(1)")
    private Boolean status;

    @Column(name = "c_parent_id", columnDefinition = "CHAR(32)")
    private String parentId;

    @Column(name = "c_order", columnDefinition = "int(1)")
    private Integer order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "t_permission_menu_to_meta",
            joinColumns = @JoinColumn(name = "c_menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "c_meta_id", referencedColumnName = "id"))
    private MenuPermissionMeta meta;

    @Transient
    private List<MenuPermission> children;
}
