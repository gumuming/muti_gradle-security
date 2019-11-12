package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "权限api")
@Table(name = "t_permission_api")
@org.hibernate.annotations.Table(appliesTo = "t_permission_menu",comment = "权限api")
public class PermissionApi extends AbstractEntity {

    @Column(name = "c_name", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '权限名称'")
    private String name;

    @Column(name = "c_url", columnDefinition = "CHAR(255) NOT NULL COMMENT '地址'")
    private String url;
}
