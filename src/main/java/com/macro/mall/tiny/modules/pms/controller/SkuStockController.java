package com.macro.mall.tiny.modules.pms.controller;


import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.ProductSaveParamsDTO;
import com.macro.mall.tiny.modules.pms.model.SkuStock;
import com.macro.mall.tiny.modules.pms.service.SkuStockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/sku")
public class SkuStockController {

    @Autowired
    SkuStockService skuStockService;

    /**
     * 编辑货品信息
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑货品信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult updateProduct(@PathVariable Long id,
                                      @RequestParam(value = "keyword",required = false) String keyword){
        List<SkuStock> list = skuStockService.getSkuInfo(id,keyword);
        return CommonResult.success(list);
    }
}

