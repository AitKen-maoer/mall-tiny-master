package com.macro.mall.tiny.modules.sms.service.impl;

import com.macro.mall.tiny.dto.HomeGoodsSaleDTO;
import com.macro.mall.tiny.modules.sms.model.HomeCategory;
import com.macro.mall.tiny.modules.sms.mapper.HomeCategoryMapper;
import com.macro.mall.tiny.modules.sms.service.HomeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-10-13
 */
@Service
public class HomeCategoryServiceImpl extends ServiceImpl<HomeCategoryMapper, HomeCategory> implements HomeCategoryService {

    @Autowired
    HomeCategoryMapper homeCategoryMapper;

    @Override
    public List<HomeGoodsSaleDTO> GetGoodsSale() {
        return homeCategoryMapper.GetGoodsSale();
    }
}
