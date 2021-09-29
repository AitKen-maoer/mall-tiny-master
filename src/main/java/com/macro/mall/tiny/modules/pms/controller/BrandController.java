package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.pms.model.Brand;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import com.macro.mall.tiny.modules.pms.service.BrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    /**
     * 获取列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductAttribute>> PropertyList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                                   @RequestParam(value = "keyword",required = false) String keyword){
        Page list = brandService.list(pageNum, pageSize,keyword);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 是否品牌制造商
     * @param ids
     * @param factoryStatus
     * @return
     */
    @ApiOperation(value = "是否品牌制造商")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    public CommonResult<CommonPage<ProductAttribute>> updateFactoryStatus(@RequestParam(value = "ids") List<Long> ids,
                                                                          @RequestParam(value = "factoryStatus") Integer factoryStatus){
        brandService.list(ids, factoryStatus);
        return CommonResult.failed();
    }
}

