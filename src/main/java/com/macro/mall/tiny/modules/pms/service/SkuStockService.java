package com.macro.mall.tiny.modules.pms.service;

import com.macro.mall.tiny.modules.pms.model.SkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface SkuStockService extends IService<SkuStock> {
    List<SkuStock> getSkuInfo(Long id,String keyword);
}
