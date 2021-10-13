package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.dto.ProductConditionDTO;
import com.macro.mall.tiny.dto.ProductSaveParamsDTO;
import com.macro.mall.tiny.dto.ProductUpdateInitDTO;
import com.macro.mall.tiny.modules.pms.model.*;
import com.macro.mall.tiny.modules.pms.mapper.ProductMapper;
import com.macro.mall.tiny.modules.pms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
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

    @Autowired
    ProductMapper productMapper;

    @Autowired
    MemberPriceService memberPriceService;
    @Autowired
    ProductLadderService productLadderService;
    @Autowired
    ProductFullReductionService productFullReductionService;
    @Autowired
    SkuStockService skuStockService;
    @Autowired
    ProductAttributeValueService productAttributeValueService;

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

    @Override
    @Transactional
    public boolean createProduct(ProductSaveParamsDTO productSaveParamsDTO) {
        Product product = productSaveParamsDTO;
        if(product.getId() != null){
            product.setId(null);
        }
        boolean save = this.save(product);
        if(save){
            switch (productSaveParamsDTO.getPromotionType()){
                case 2:
                    // 2. 会员价格
                    SaveManyList(productSaveParamsDTO.getMemberPriceList(),product.getId(),memberPriceService);
                    break;
                case 3:
                    // 3. 阶梯价格
                    SaveManyList(productSaveParamsDTO.getProductLadderList(),product.getId(),productLadderService);
                    break;
                case 4:
                    // 4. 减满价格
                    SaveManyList(productSaveParamsDTO.getProductFullReductionList(),product.getId(),productFullReductionService);
                    break;
            }
            // 5. sku
            SaveManyList(productSaveParamsDTO.getSkuStockList(),product.getId(), skuStockService);
            // 6 spu
            SaveManyList(productSaveParamsDTO.getProductAttributeValueList(),product.getId(), productAttributeValueService);
        }
        return save;
    }

    /**
     * 公共方法： 保存会员价格、阶梯价格、减满价格、 sku 、 spu 商品的关联数据
     * 统一： 都需要设置商品id,  都需要批量保存
     * @param list
     * @param productId
     * @param service
     */
    private void SaveManyList(List list, Long productId, IService service) {
        //如果list集合为空或者没有长度直接结束保存
        if(CollectionUtils.isEmpty(list)) return;
        try {
            for (Object obj: list) {
                Method setProductId = obj.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(obj,(Long)null);
                setProductId.invoke(obj, productId);
            }
            service.saveBatch(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductUpdateInitDTO getUpdateInfo(Long id) {
        return productMapper.getUpdateInfo(id);
    }

    @Override
    @Transactional
    public boolean updateProduct(ProductSaveParamsDTO productSaveParamsDTO) {
        Product product = productSaveParamsDTO;
        boolean result = this.updateById(product);
        if(result){
            switch (productSaveParamsDTO.getPromotionType()){
                case 2:
                    DeleteManyListByProductId(product.getId(),memberPriceService);
                    // 2. 会员价格
                    SaveManyList(productSaveParamsDTO.getMemberPriceList(),product.getId(),memberPriceService);
                    break;
                case 3:
                    DeleteManyListByProductId(product.getId(),productLadderService);
                    // 3. 阶梯价格
                    SaveManyList(productSaveParamsDTO.getProductLadderList(),product.getId(),productLadderService);
                    break;
                case 4:
                    DeleteManyListByProductId(product.getId(),productFullReductionService);
                    // 4. 减满价格
                    SaveManyList(productSaveParamsDTO.getProductFullReductionList(),product.getId(),productFullReductionService);
                    break;
            }
            DeleteManyListByProductId(product.getId(),skuStockService);
            // 5. sku
            SaveManyList(productSaveParamsDTO.getSkuStockList(),product.getId(), skuStockService);

            DeleteManyListByProductId(product.getId(),productAttributeValueService);
            // 6 spu
            SaveManyList(productSaveParamsDTO.getProductAttributeValueList(),product.getId(), productAttributeValueService);
        }
        return result;
    }

    private void DeleteManyListByProductId(Long id, IService service) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",id);
        service.remove(queryWrapper);
    }

}
