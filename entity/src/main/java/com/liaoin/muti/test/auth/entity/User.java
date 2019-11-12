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
@Table(name = "t_user")
public class User extends AbstractEntity {

    @Column(name = "c_user_id", columnDefinition = "CHAR(32) NOT NULL COMMENT '用户ID'")
    private String userId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = @JoinColumn(name = "c_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "c_role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
