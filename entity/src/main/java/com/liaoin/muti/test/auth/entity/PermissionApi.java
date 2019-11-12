package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_permission_api")
public class PermissionApi extends AbstractEntity {

    @Column(name = "c_name", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '权限名称'")
    private String name;

    @Column(name = "c_url", columnDefinition = "CHAR(255) NOT NULL COMMENT '地址'")
    private String url;
}
