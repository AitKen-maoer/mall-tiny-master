package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.ProductAttributeCategoryDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import com.macro.mall.tiny.modules.pms.model.ProductAttributeCategory;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.macro.mall.tiny.modules.pms.service.ProductAttributeCategoryService;
import com.macro.mall.tiny.modules.pms.service.ProductAttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/productAttribute/category")
public class ProductAttributeCategoryController {

    @Autowired
    ProductAttributeCategoryService productAttributeCategoryService;

    @Autowired
    ProductAttributeService productAttributeService;

    /**
     * 列表获取
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "列表数据获取")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductAttributeCategory>> getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        Page list = productAttributeCategoryService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 新增
     * @param name
     * @return
     */
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult categoryCreate(@RequestParam(value = "name") String name){
        ProductAttributeCategory productAttributeCategory = new ProductAttributeCategory();
        productAttributeCategory.setName(name);
        boolean saveProductAttribute = productAttributeCategoryService.save(productAttributeCategory);
        if(saveProductAttribute){
            return CommonResult.success(saveProductAttribute);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 编辑
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult categoryUpdate(@PathVariable(value = "id") Long id, @RequestParam(value = "name") String name){
        ProductAttributeCategory productAttributeCategory = new ProductAttributeCategory();
        productAttributeCategory.setId(id);
        productAttributeCategory.setName(name);
        boolean b = productAttributeCategoryService.updateById(productAttributeCategory);
        if(b){
            return CommonResult.success(b);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult categoryDelete(@PathVariable(value = "id") Long id){
        boolean b = productAttributeCategoryService.removeById(id);
        if(b){
            return CommonResult.success(b);
        }else{
            return CommonResult.failed();
        }
    }

    /**
     * 筛选属性列表
     * @return
     */
    @ApiOperation(value = "筛选属性列表")
    @RequestMapping(value = "list/withAttr", method = RequestMethod.GET)
    public CommonResult listWithAttr(){
      List<ProductAttributeCategoryDTO> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }

}

