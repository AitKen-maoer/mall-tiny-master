package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.ProductConditionDTO;
import com.macro.mall.tiny.modules.pms.model.Product;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.macro.mall.tiny.modules.pms.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 列表获取
     * @param productConditionDTO
     * @return
     */
    @ApiOperation(value = "列表数据获取")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductCategory>> getList(ProductConditionDTO productConditionDTO){
        Page list = productService.list(productConditionDTO);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 删除和批量删除
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除和批量删除")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids){
        boolean b = productService.removeByIds(ids);
        if(b){
            return CommonResult.success(b);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 是否上架
     * @param ids
     * @param publishStatus
     * @return
     */
    @ApiOperation(value = "是否上架")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    public CommonResult updatePublishStatus(@RequestParam(value = "ids") List<Long> ids,
                                            @RequestParam(value = "publishStatus") Integer publishStatus){
        boolean result = productService.updataNewStatus(ids, publishStatus, Product::getPublishStatus);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 是否新品
     * @param ids
     * @param newStatus
     * @return
     */
    @ApiOperation(value = "是否新品")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    public CommonResult updateNewStatus(@RequestParam(value = "ids") List<Long> ids,
                                        @RequestParam(value = "newStatus") Integer newStatus){
        boolean result = productService.updataNewStatus(ids, newStatus, Product::getNewStatus);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 是否推荐
     * @param ids
     * @param recommendStatus
     * @return
     */
    @ApiOperation(value = "是否推荐")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    public CommonResult updateRecommendStatus(@RequestParam(value = "ids") List<Long> ids,
                                              @RequestParam(value = "recommendStatus") Integer recommendStatus){
        boolean result = productService.updataNewStatus(ids, recommendStatus, Product::getRecommandStatus);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }


}


