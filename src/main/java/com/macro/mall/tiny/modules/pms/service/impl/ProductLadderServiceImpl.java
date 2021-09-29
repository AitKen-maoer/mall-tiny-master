package com.macro.mall.tiny.modules.pms.service.impl;

import com.macro.mall.tiny.modules.pms.model.ProductLadder;
import com.macro.mall.tiny.modules.pms.mapper.ProductLadderMapper;
import com.macro.mall.tiny.modules.pms.service.ProductLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class ProductLadderServiceImpl extends ServiceImpl<ProductLadderMapper, ProductLadder> implements ProductLadderService {

}
