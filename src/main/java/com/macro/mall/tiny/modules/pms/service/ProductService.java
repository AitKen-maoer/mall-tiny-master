package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.ProductConditionDTO;
import com.macro.mall.tiny.modules.pms.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductService extends IService<Product> {

    Page list(ProductConditionDTO productConditionDTO);

    boolean updataNewStatus(List<Long> ids, Integer newStatus, SFunction<Product, ?> getNewStatus);
}
