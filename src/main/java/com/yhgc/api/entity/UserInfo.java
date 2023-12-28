package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserInfo2对象", description="用户")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "姓名")
    @TableField("Name")
    @JsonProperty("Name")
    private String Name;

    @ApiModelProperty(value = "账号")
    @TableField("Account")
    @JsonProperty("Account")
    private String Account;

    @ApiModelProperty(value = "密码;加密存储")
    @TableField("Password")
    @JsonProperty("Password")
    private String Password;

    @ApiModelProperty(value = "性别;1男 0女")
    @TableField("Sex")
    @JsonProperty("Sex")
    private Integer Sex;

    @ApiModelProperty(value = "用户状态;1正常  0禁止")
    @TableField("Valid")
    @JsonProperty("Valid")
    private Integer Valid;

    @ApiModelProperty(value = "手机号码")
    @TableField("Phone")
    @JsonProperty("Phone")
    private String Phone;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("Email")
    @JsonProperty("Email")
    private String Email;

    @ApiModelProperty(value = "上岗证号")
    @TableField("JobNo")
    @JsonProperty("JobNo")
    private String JobNo;

    @ApiModelProperty(value = "工号")
    @TableField("WorkNo")
    @JsonProperty("WorkNo")
    private String WorkNo;

    @ApiModelProperty(value = "所属部门（ID）")
    @TableField("FromStructId")
    @JsonProperty("FromStructId")
    private Integer FromStructId;

    @ApiModelProperty(value = "查看部门所有数据")
    @TableField("LookAllData")
    @JsonProperty("LookAllData")
    private Integer LookAllData;

    @ApiModelProperty(value = "查看所有踏勘记录")
    @TableField("LookAllSurvey")
    @JsonProperty("LookAllSurvey")
    private Integer LookAllSurvey;

    @ApiModelProperty(value = "检测负责人")
    @TableField("TestBoss")
    @JsonProperty("TestBoss")
    private Integer TestBoss;

    @ApiModelProperty(value = "所属角色（ID）")
    @TableField("FromRoleId")
    @JsonProperty("FromRoleId")
    private Integer FromRoleId;

    @ApiModelProperty(value = "个人签章")
    @TableField("Sign")
    @JsonProperty("Sign")
    private String Sign;

    @ApiModelProperty(value = "创建日期")
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreateDate;

    @ApiModelProperty(value = "验证码")
    @TableField(exist = false)
    private String code;

}
