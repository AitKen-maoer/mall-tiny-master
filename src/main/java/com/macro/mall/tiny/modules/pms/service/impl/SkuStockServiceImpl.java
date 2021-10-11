package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.pms.model.SkuStock;
import com.macro.mall.tiny.modules.pms.mapper.SkuStockMapper;
import com.macro.mall.tiny.modules.pms.service.SkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {
    @Override
    public List<SkuStock> getSkuInfo(Long id,String keyword) {
        QueryWrapper<SkuStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SkuStock::getProductId,id);
        if(keyword !=null&&!keyword.isEmpty()){
            queryWrapper.lambda().eq(SkuStock::getSkuCode,keyword);
        }
        return this.list(queryWrapper);
    }
}
