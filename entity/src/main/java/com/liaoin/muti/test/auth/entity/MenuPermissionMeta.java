package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "权限元数据")
@Table(name = "t_permission_menu_meta")
@org.hibernate.annotations.Table(appliesTo = "t_permission_menu_meta",comment = "权限元数据")
public class MenuPermissionMeta extends AbstractEntity {

    @Column(name = "c_title", columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(name = "c_default_icon", columnDefinition = "VARCHAR(255)")
    private String defaultIcon;

    @Column(name = "c_active_icon", columnDefinition = "VARCHAR(255)")
    private String activeIcon;
}
