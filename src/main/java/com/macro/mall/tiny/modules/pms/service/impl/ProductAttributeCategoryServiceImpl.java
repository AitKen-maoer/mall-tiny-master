package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.ProductAttributeCategoryDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttributeCategory;
import com.macro.mall.tiny.modules.pms.mapper.ProductAttributeCategoryMapper;
import com.macro.mall.tiny.modules.pms.service.ProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

    @Autowired
    ProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public Page list(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public List<ProductAttributeCategoryDTO> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }
}
