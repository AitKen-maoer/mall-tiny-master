package com.macro.mall.tiny.modules.sms.mapper;

import com.macro.mall.tiny.dto.HomeGoodsSaleDTO;
import com.macro.mall.tiny.modules.sms.model.HomeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-10-13
 */
public interface HomeCategoryMapper extends BaseMapper<HomeCategory> {

    List<HomeGoodsSaleDTO> GetGoodsSale();
}
