package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DcSbPicture对象", description="")
public class DcSbPicture implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "BasicInfoId", type = IdType.AUTO)
    @JsonProperty("BasicInfoId")
    private Integer BasicInfoId;

    @TableField("BaseInfoId")
    @JsonProperty("BaseInfoId")
    private String BaseInfoId;

    @TableField("ImageUrl")
    @JsonProperty("ImageUrl")
    private String ImageUrl;

    @TableField("ComId")
    @JsonProperty("ComId")
    private String ComId;


}
