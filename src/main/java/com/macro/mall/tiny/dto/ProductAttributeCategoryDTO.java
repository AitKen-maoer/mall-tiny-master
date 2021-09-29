package com.macro.mall.tiny.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="筛选属性对象", description="筛选属性DTO")
public class ProductAttributeCategoryDTO {
    private Long id;

    private String name;

    private List<ProductAttribute> productAttributeList;
}
