package com.macro.mall.tiny.modules.pms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_attribute_category")
@ApiModel(value="ProductAttributeCategory对象", description="产品属性分类表")
public class ProductAttributeCategory implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "属性数量")
    private Integer attributeCount;

    @ApiModelProperty(value = "参数数量")
    private Integer paramCount;


}
