package com.liaoin.muti.test.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.jsonwebtoken.lang.Assert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @program: security_demo
 * @description: user
 * @author: JingDe Ran
 * @create: 2019-10-25 10:48
 * @Email: sarakarma49@gmail.com
 */
@Entity
@Getter
@Setter
@Builder
@ApiModel(value = "用户")
@NameStyle(Style.camelhump)
@AllArgsConstructor
@org.hibernate.annotations.Table(appliesTo = "t_user_info", comment = "用户")
@Table(name = "t_user_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class UserInfo  implements UserDetails, CredentialsContainer {

    @Transient
    @ApiModelProperty(hidden = true)
    private  Set<GrantedAuthority> authorities;
    @Transient
    @ApiModelProperty(hidden = true)
    private  boolean accountNonExpired;
    @Transient
    @ApiModelProperty(hidden = true)
    private  boolean accountNonLocked;
    @Transient
    @ApiModelProperty(hidden = true)
    private  boolean credentialsNonExpired;
    @Transient
    @ApiModelProperty(hidden = true)
    private  boolean enabled;

    public UserInfo(){}

    public UserInfo(Integer id, String username, String password,boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities){
        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("不能将空值或空值传递给构造函数");
        }
        this.id = id;
        this.username = username;
        this.password = password;

        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "无法传递空的GrantedAuthority集合");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority列表不能包含任何空元素");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Id
    @Min(0)
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty(value="系统标识")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="int(11) COMMENT '系统标识'")
    private Integer id;


    @ApiModelProperty(value="用户名具有唯一约束")
    @Column(columnDefinition="varchar(255) COMMENT '用户名具有唯一约束'")
    @NotBlank(message = "参数不能为为空或空串")
    private String username;

    @ApiModelProperty(value="密码")
    @Column(columnDefinition="varchar(255) COMMENT '密码'")
    private String password;

    @ApiModelProperty("排序字段")
    @Column(columnDefinition="int(11) COMMENT '排序字段'")
    private Integer userInfoIndex=1;

    @ApiModelProperty(value = "最后登陆时间",hidden = true)
    @Column(columnDefinition="datetime COMMENT '最后登陆时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime landingTime;

    @ApiModelProperty(name = "是否启用",hidden = true)
    private Integer isDelete = 0;







}