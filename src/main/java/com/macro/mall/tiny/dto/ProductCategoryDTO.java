package com.macro.mall.tiny.dto;

import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductCategory对象", description="用于商品分类添加，产品修改")
public class ProductCategoryDTO extends ProductCategory {
    List<Long> productAttributeIdList;
}
