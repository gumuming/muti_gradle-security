package com.liaoin.muti.test.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/***************************************
 * @Author: jingDe Ran
 * @Date: 2019/7/1/14:42
 * @Email: sarakarma49@gmail.com  
 ***************************************/
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "秩序报警管理")
@Table(name = "t_property_order")
@org.hibernate.annotations.Table(appliesTo = "t_property_order",comment = "秩序管理")
public class PropertyOrder implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int COMMENT '主键'")
    private Integer id;

    @Column(name = "CREATE_AT", columnDefinition = "DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间'")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createAt;


    @Column(name = "UPDATE_AT",
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '更新时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateAt;

    @ApiModelProperty("授权人姓名")
    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '授权人姓名'")
    @NotBlank( message = "授权人姓名不能为空")
    private String authName;

    @ApiModelProperty("授权人id")
    @Column(columnDefinition = "int NOT NULL COMMENT '授权人id'")
    @NotBlank(message = "授权人id不能为空")
    private Integer authId;

    @ApiModelProperty("授权密码")
    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '授权密码'")

    private String authPassWord;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '授权人职位'")

    @ApiModelProperty("授权人职位")
    private String authPosition;

    @Column(columnDefinition = "decimal(19,2) NOT NULL COMMENT '每月授权额'")
    @ApiModelProperty("每月授权额")

    private BigDecimal authMonthCount ;

    @Column(columnDefinition = "decimal(19,2) COMMENT '已授权额'")
    @ApiModelProperty("已授权额")
    private BigDecimal authorizedCount = new BigDecimal(0);

    @ApiModelProperty("门店号 ")
    @Column(columnDefinition = "varchar(255)  comment '门店号 '")
    private String storeName;





}
