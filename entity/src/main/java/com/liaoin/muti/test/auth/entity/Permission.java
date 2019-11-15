package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "权限")
@Table(name = "t_permission")
@org.hibernate.annotations.Table(appliesTo = "t_permission",comment = "权限")
public class Permission extends AbstractEntity {

    @Column(name = "c_name", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '权限名称'")
    private String name;

    @Column(name = "c_parent_id", columnDefinition = "CHAR(32) COMMENT '父权限ID'")
    private String parentId;

    @Column(name = "c_url", columnDefinition = "CHAR(255) NOT NULL COMMENT '地址'")
    private String url;

    @Transient
    private List<Permission> children;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_permission_permission_api",
            joinColumns = @JoinColumn(name = "c_permission_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "c_permission_api_id", referencedColumnName = "id"))
    private Set<PermissionApi> apis;
}
