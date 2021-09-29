package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.RelationAttrInfoDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import com.macro.mall.tiny.modules.pms.mapper.ProductAttributeMapper;
import com.macro.mall.tiny.modules.pms.model.ProductAttributeCategory;
import com.macro.mall.tiny.modules.pms.service.ProductAttributeCategoryService;
import com.macro.mall.tiny.modules.pms.service.ProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Autowired
    ProductAttributeMapper productAttributeMapper;

    @Autowired
    ProductAttributeCategoryService productAttributeCategoryService;

    @Override
    public Page propertyList(Long cid, Integer type, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<ProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductAttribute::getProductAttributeCategoryId,cid).eq(ProductAttribute::getType,type);
        return this.page(page,queryWrapper);
    }

    @Override
    @Transactional
    public boolean saveCreate(ProductAttribute productAttribute) {
        boolean save = this.save(productAttribute);
        if(save){
            UpdateWrapper<ProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
            // 属性
            if(productAttribute.getType() == 0){
                updateWrapper.setSql("attribute_count=attribute_count+1");
            }
            // 参数
            else if(productAttribute.getType() == 1){
                updateWrapper.setSql("param_count=param_count+1");
            }
            updateWrapper.lambda().eq(ProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            productAttributeCategoryService.update(updateWrapper);
        }
        return save;
    }

    @Override
    @Transactional
    public boolean deleteLists(List<Long> ids) {
        ProductAttribute productAttribute = null;
        for (Long id : ids){
            productAttribute = this.getById(id);
            if(productAttribute != null){
                break;
            }
        }
        int deleteLength = productAttributeMapper.deleteBatchIds(ids);
        if(deleteLength > 0 && productAttribute != null){
            UpdateWrapper<ProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
            // 属性
            if(productAttribute.getType() == 0){
                updateWrapper.setSql("attribute_count=attribute_count-" + deleteLength);
            }
            // 参数
            else if(productAttribute.getType() == 1){
                updateWrapper.setSql("param_count=param_count-" + deleteLength);
            }
            updateWrapper.lambda().eq(ProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            boolean update = productAttributeCategoryService.update(updateWrapper);
            return update;
        }
            return false;
    }

    @Override
    public List<RelationAttrInfoDTO> getProductCategoryById(Long id) {
        return productAttributeMapper.CategoryById(id);
    }
}
