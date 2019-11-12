package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_permission_menu_meta")
public class MenuPermissionMeta extends AbstractEntity {

    @Column(name = "c_title", columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(name = "c_default_icon", columnDefinition = "VARCHAR(255)")
    private String defaultIcon;

    @Column(name = "c_active_icon", columnDefinition = "VARCHAR(255)")
    private String activeIcon;
}
