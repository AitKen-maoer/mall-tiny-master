package com.macro.mall.tiny.modules.pms.service;

import com.macro.mall.tiny.dto.RelationAttrInfoDTO;
import com.macro.mall.tiny.modules.pms.model.ProductCategoryAttributeRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductCategoryAttributeRelationService extends IService<ProductCategoryAttributeRelation> {
}
