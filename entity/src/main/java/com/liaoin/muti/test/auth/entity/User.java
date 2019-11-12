package com.liaoin.muti.test.auth.entity;


import com.liaoin.muti.test.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "权限-用户")
@Table(name = "t_user")
@org.hibernate.annotations.Table(appliesTo = "t_permission_menu",comment = "权限-用户")
public class User extends AbstractEntity {

    @Column(name = "c_user_id", columnDefinition = "CHAR(32) NOT NULL COMMENT '用户ID'")
    private String userId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = @JoinColumn(name = "c_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "c_role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
