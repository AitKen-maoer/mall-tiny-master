package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.ProductConditionDTO;
import com.macro.mall.tiny.modules.pms.model.Product;
import com.macro.mall.tiny.modules.pms.mapper.ProductMapper;
import com.macro.mall.tiny.modules.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public Page list(ProductConditionDTO productConditionDTO) {
        Page page = new Page(productConditionDTO.getPageNum(),productConditionDTO.getPageSize());
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Product> lambdaWrapper = queryWrapper.lambda();
        if(!StringUtils.isEmpty(productConditionDTO.getKeyword())){
            lambdaWrapper.like(Product::getName,productConditionDTO.getKeyword());
        }
        if(!StringUtils.isEmpty(productConditionDTO.getProductSn())){
            lambdaWrapper.eq(Product::getProductSn,productConditionDTO.getProductSn());
        }
        if(productConditionDTO.getProductCategoryId() != null){
            lambdaWrapper.eq(Product::getProductCategoryId,productConditionDTO.getProductCategoryId());
        }
        if(productConditionDTO.getBrandId() != null){
            lambdaWrapper.eq(Product::getBrandId,productConditionDTO.getBrandId());
        }
        if(productConditionDTO.getPublishStatus() != null){
            lambdaWrapper.eq(Product::getPublishStatus,productConditionDTO.getPublishStatus());
        }
        if(productConditionDTO.getVerifyStatus() != null){
            lambdaWrapper.eq(Product::getVerifyStatus,productConditionDTO.getVerifyStatus());
        }
        lambdaWrapper.orderByDesc(Product::getSort);
        return this.page(page,queryWrapper);
    }

    @Override
    public boolean updataNewStatus(List<Long> ids, Integer publishStatus, SFunction<Product, ?> getPublishStatus) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(getPublishStatus,publishStatus).in(Product::getId,ids);
        return this.update(updateWrapper);
    }
}
