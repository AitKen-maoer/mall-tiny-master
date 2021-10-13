package com.macro.mall.tiny.modules.sms.service;

import com.macro.mall.tiny.dto.HomeGoodsSaleDTO;
import com.macro.mall.tiny.modules.sms.model.HomeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-10-13
 */
public interface HomeCategoryService extends IService<HomeCategory> {

    List<HomeGoodsSaleDTO> GetGoodsSale();
}
