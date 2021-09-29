package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.dto.RelationAttrInfoDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductAttributeMapper extends BaseMapper<ProductAttribute> {

    List<RelationAttrInfoDTO> CategoryById(Long id);
}
