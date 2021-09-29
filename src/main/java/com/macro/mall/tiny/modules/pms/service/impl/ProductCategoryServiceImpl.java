package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.ProductCateChildrenDTO;
import com.macro.mall.tiny.dto.ProductCategoryDTO;
import com.macro.mall.tiny.modules.pms.mapper.ProductAttributeCategoryMapper;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.macro.mall.tiny.modules.pms.mapper.ProductCategoryMapper;
import com.macro.mall.tiny.modules.pms.model.ProductCategoryAttributeRelation;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryAttributeRelationService;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public Page list(Long parentId, Integer pageNum, Integer pageSize) {

        Page page = new Page(pageNum,pageSize);
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductCategory::getParentId, parentId);

        return this.page(page,queryWrapper);
    }

    @Override
    public boolean navStatus(Integer ids, Integer navStatus) {
        UpdateWrapper<ProductCategory> objectUpdateWrapper = new UpdateWrapper<>();
        LambdaUpdateWrapper<ProductCategory> in = objectUpdateWrapper.lambda().set(ProductCategory::getNavStatus, navStatus).in(ProductCategory::getId, ids);
        return this.update(in);
    }

    @Override
    public boolean showStatus(Integer ids, Integer showStatus) {
        UpdateWrapper<ProductCategory> objectUpdateWrapper = new UpdateWrapper<>();
        LambdaUpdateWrapper<ProductCategory> in = objectUpdateWrapper.lambda().set(ProductCategory::getShowStatus, showStatus).in(ProductCategory::getId, ids);
        return this.update(in);
    }

    @Override
    public Page list(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean createSave(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDTO,productCategory);
        productCategory.setProductCount(0);
        if(productCategory.getParentId() == 0){
            productCategory.setLevel(0);
        }else{
            productCategory.setLevel(1);
        }
        boolean save = this.save(productCategory);

        List<Long> productAttributeIdList = productCategoryDTO.getProductAttributeIdList();
        ArrayList<ProductCategoryAttributeRelation> list = new ArrayList<>();
        for (Long attrId : productAttributeIdList) {
            ProductCategoryAttributeRelation productCategoryAttributeRelation = new ProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            list.add(productCategoryAttributeRelation);
        }
        boolean b = productCategoryAttributeRelationService.saveBatch(list);
        if(productAttributeIdList.size() > 0){
            return save && b;
        }else{
            return save;
        }
    }

    @Override
    public boolean updateSave(ProductCategoryDTO productCategoryDTO) {
        // 更新商品分类
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDTO,productCategory);
        if(productCategory.getParentId() == 0){
            productCategory.setLevel(0);
        }else{
            productCategory.setLevel(1);
        }
        boolean save = this.updateById(productCategory);
        QueryWrapper<ProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductCategoryAttributeRelation::getProductCategoryId,productCategory.getId());
        productCategoryAttributeRelationService.remove(queryWrapper);
        List<Long> productAttributeIdList = productCategoryDTO.getProductAttributeIdList();
        ArrayList<ProductCategoryAttributeRelation> relation = new ArrayList<>();
        for(Long attrId : productAttributeIdList){
            ProductCategoryAttributeRelation productCategoryAttributeRelation = new ProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            relation.add(productCategoryAttributeRelation);
        }
        boolean b = productCategoryAttributeRelationService.saveBatch(relation);
        if(productAttributeIdList.size() > 0){
            return save && b;
        }else{
            return save;
        }
    }

    @Override
    public List<ProductCateChildrenDTO> getListWithAttr() {
        return productCategoryMapper.getWithChildren();
    }
}
