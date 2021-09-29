package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.RelationAttrInfoDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    Page propertyList(Long cid, Integer type, Integer pageNum, Integer pageSize);

    boolean saveCreate(ProductAttribute productAttribute);

    boolean deleteLists(List<Long> ids);

    List<RelationAttrInfoDTO> getProductCategoryById(Long id);
}
