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
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SbBasicinfo对象", description="")
public class SbBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BasicInfoId", type = IdType.AUTO)
    @JsonProperty("BasicInfoId")
    private Integer BasicInfoId;

    @ApiModelProperty(value = "设备编号")
    @TableField("MachineId")
    @JsonProperty("MachineId")
    private String MachineId;

    @ApiModelProperty(value = "检测流水号")
    @TableField("SerialNo")
    @JsonProperty("SerialNo")
    private String SerialNo;

    @ApiModelProperty(value = "桩号")
    @TableField("PileNo")
    @JsonProperty("PileNo")
    private String PileNo;

    @ApiModelProperty(value = "检测时间")
    @TableField("TestTime")
    @JsonProperty("TestTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date TestTime;

    @ApiModelProperty(value = "桩长")
    @TableField("PileLength")
    @JsonProperty("PileLength")
    private Double PileLength;

    @ApiModelProperty(value = "桩径")
    @TableField("PileDiameter")
    @JsonProperty("PileDiameter")
    private String PileDiameter;

    @ApiModelProperty(value = "混凝土强度")
    @TableField("ConcreteStrength")
    @JsonProperty("ConcreteStrength")
    private String ConcreteStrength;

    @ApiModelProperty(value = "声测管数据")
    @TableField("TubeCount")
    @JsonProperty("TubeCount")
    private Integer TubeCount;

    @ApiModelProperty(value = "测试剖面数量")
    @TableField("SectionCount")
    @JsonProperty("SectionCount")
    private Integer SectionCount;

    @ApiModelProperty(value = "测试步距（mm）")
    @TableField("Step")
    @JsonProperty("Step")
    private Integer Step;

    @ApiModelProperty(value = "1号声测管北偏角（°）")
    @TableField("Angle")
    @JsonProperty("Angle")
    private Integer Angle;

    @ApiModelProperty(value = "GPS定位信息是否有效")
    @TableField("GpsIsValid")
    @JsonProperty("GpsIsValid")
    private Integer GpsIsValid;

    @ApiModelProperty(value = "GPS经度（°）")
    @TableField("GpsLongitude")
    @JsonProperty("GpsLongitude")
    private Float GpsLongitude;

    @ApiModelProperty(value = "GPS纬度（°）")
    @TableField("GpsLatitude")
    @JsonProperty("GpsLatitude")
    private Float GpsLatitude;

    @ApiModelProperty(value = "检测人员上岗证编号")
    @TableField("ShangGangZheng")
    @JsonProperty("ShangGangZheng")
    private String ShangGangZheng;

    @ApiModelProperty(value = "上传时间")
    @TableField("CreateTime")
    @JsonProperty("CreateTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date CreateTime;

    @ApiModelProperty(value = "上传设备厂商标识")
    @TableField("CreateName")
    @JsonProperty("CreateName")
    private String CreateName;

    @ApiModelProperty(value = "试验标识")
    @TableField("BaseInfoId")
    @JsonProperty("BaseInfoId")
    private String BaseInfoId;

    @ApiModelProperty(value = "数据有效性")
    @TableField("IsValid")
    @JsonProperty("IsValid")
    private Integer IsValid;

    @ApiModelProperty(value = "检测单位编码")
    @TableField("ComId")
    @JsonProperty("ComId")
    private String ComId;

    @ApiModelProperty(value = "原始数据url")
    @TableField("DataUrl")
    @JsonProperty("DataUrl")
    private String DataUrl;


    @ApiModelProperty(value = "压缩文件")
    @TableField(exist = false)
    @JsonProperty("ZipFileData")
    private byte[] ZipFileData;


}
