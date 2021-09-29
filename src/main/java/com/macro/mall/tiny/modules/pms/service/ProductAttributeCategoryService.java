package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.ProductAttributeCategoryDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {

    Page list(Integer pageNum, Integer pageSize);

    List<ProductAttributeCategoryDTO> getListWithAttr();
}
