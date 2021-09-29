package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.model.Brand;
import com.macro.mall.tiny.modules.pms.mapper.BrandMapper;
import com.macro.mall.tiny.modules.pms.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Override
    public Page list(Integer pageNum, Integer pageSize,String keyword) {
        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<>();
        Page page = new Page(pageNum,pageSize);
        if(!(keyword == null || "".equals(keyword))){
            brandQueryWrapper.lambda().like(Brand::getName,keyword);
        }
        brandQueryWrapper.lambda().orderByAsc(Brand::getSort);
        return this.page(page,brandQueryWrapper);
    }

    @Override
    public void list(List<Long> ids, Integer factoryStatus) {
        UpdateWrapper<Brand> brandUpdateWrapper = new UpdateWrapper<>();
        brandUpdateWrapper.lambda().set(Brand::getFactoryStatus,factoryStatus);
    }
}
