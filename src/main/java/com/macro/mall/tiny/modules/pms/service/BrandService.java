package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.model.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface BrandService extends IService<Brand> {

    Page list(Integer pageNum, Integer pageSize,String keyword);

    void list(List<Long> ids, Integer factoryStatus);
}
