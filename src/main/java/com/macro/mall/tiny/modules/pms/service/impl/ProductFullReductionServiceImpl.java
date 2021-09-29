package com.macro.mall.tiny.modules.pms.service.impl;

import com.macro.mall.tiny.modules.pms.model.ProductFullReduction;
import com.macro.mall.tiny.modules.pms.mapper.ProductFullReductionMapper;
import com.macro.mall.tiny.modules.pms.service.ProductFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductFullReductionServiceImpl extends ServiceImpl<ProductFullReductionMapper, ProductFullReduction> implements ProductFullReductionService {

}
