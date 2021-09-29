package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.dto.RelationAttrInfoDTO;
import com.macro.mall.tiny.modules.pms.model.ProductCategoryAttributeRelation;
import com.macro.mall.tiny.modules.pms.mapper.ProductCategoryAttributeRelationMapper;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements ProductCategoryAttributeRelationService {

    @Autowired
    ProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

}
