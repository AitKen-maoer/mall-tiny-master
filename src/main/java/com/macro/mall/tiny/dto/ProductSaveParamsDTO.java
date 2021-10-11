package com.macro.mall.tiny.dto;

import com.macro.mall.tiny.modules.pms.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductConditionDTO商品保存数据传输对象", description="用于商品的添加、修改保存的参数接收")
public class ProductSaveParamsDTO extends Product {

    // 会员价格
    @ApiModelProperty(value = "会员价格")
    private List<MemberPrice> memberPriceList;
    // 商品满减
    @ApiModelProperty(value = "商品满减")
    private List<ProductFullReduction> productFullReductionList;
    // 商品阶梯价格
    @ApiModelProperty(value = "商品阶梯价格")
    private List<ProductLadder> productLadderList;
    // 商品属性相关
    @ApiModelProperty(value = "商品属性相关")
    private List<ProductAttributeValue> productAttributeValueList;
    // 商品sku库存信息
    @ApiModelProperty(value = "商品sku库存信息")
    @Size(min = 1,message = "SKU至少要有一项")
    @Valid   // 嵌套验证支持
    private List<SkuStock> skuStockList;
}
