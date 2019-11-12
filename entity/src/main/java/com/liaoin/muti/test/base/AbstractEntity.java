package com.liaoin.muti.test.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "ID", updatable = false, columnDefinition = "CHAR(32) NOT NULL COMMENT '主键'")
    private String id;

    @Column(name = "CREATE_AT", insertable = false, updatable = false, columnDefinition = "DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间'")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT", insertable = false, updatable = false,
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '更新时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "CREATE_BY", updatable = false, columnDefinition = "CHAR(255) NOT NULL COMMENT '创建人'")
    private String createBy = "system";

    @LastModifiedBy
    @Column(name = "UPDATE_BY", columnDefinition = "CHAR(255) NOT NULL COMMENT '更新人'")
    private String updateBy = "system";


    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
