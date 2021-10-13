package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.dto.HomeMenusDTO;
import com.macro.mall.tiny.dto.ProductConditionDTO;
import com.macro.mall.tiny.dto.ProductUpdateInitDTO;
import com.macro.mall.tiny.modules.pms.model.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductMapper extends BaseMapper<Product> {

    ProductUpdateInitDTO getUpdateInfo(Long id);
}
