package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.HomeGoodsSaleDTO;
import com.macro.mall.tiny.dto.HomeMenusDTO;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryService;
import com.macro.mall.tiny.modules.sms.model.HomeAdvertise;
import com.macro.mall.tiny.modules.sms.service.HomeAdvertiseService;
import com.macro.mall.tiny.modules.sms.service.HomeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    HomeCategoryService homeCategoryService;

    @Autowired
    HomeAdvertiseService homeAdvertiseService;
    /**
     * 获取首页类型导航栏和数据
     * @return
     */
    @ApiOperation(value = "获取首页类型导航栏和数据")
    @RequestMapping(value = "/menus_banner", method = RequestMethod.GET)
    public CommonResult menusBanner(){
        List<HomeMenusDTO> menus = productCategoryService.getMenus();
        List<HomeAdvertise> list = homeAdvertiseService.list();
        HashMap<Object, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("homeMenusList",menus);
        objectObjectMap.put("homeAdvertisesList",list);
        return CommonResult.success(objectObjectMap);
    }

    /**
     * 获取分类推进、分类推进对应的推进商品
     * @return
     */
    @ApiOperation(value = "获取分类推进、分类推进对应的推进商品")
    @RequestMapping(value = "/goods_sale", method = RequestMethod.GET)
    public CommonResult goodsSale(){
        List<HomeGoodsSaleDTO> homeGoodsSale = homeCategoryService.GetGoodsSale();
        return CommonResult.success(homeGoodsSale);
    }


}
