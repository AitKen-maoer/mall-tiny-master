package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.dto.ProductCateChildrenDTO;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<ProductCateChildrenDTO> getWithChildren();
}
