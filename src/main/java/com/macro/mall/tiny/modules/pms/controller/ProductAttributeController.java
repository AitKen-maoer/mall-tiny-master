package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.RelationAttrInfoDTO;
import com.macro.mall.tiny.modules.pms.model.ProductAttribute;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.macro.mall.tiny.modules.pms.model.ProductCategoryAttributeRelation;
import com.macro.mall.tiny.modules.pms.service.ProductAttributeService;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryAttributeRelationService;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/productAttribute")
public class ProductAttributeController {

    @Autowired
    ProductAttributeService productAttributeService;

    @Autowired
    ProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    /**
     * 属性列表
     * @param cid
     * @return
     */
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductAttribute>> PropertyList(@PathVariable(value = "cid") Long cid,
                                     @RequestParam(value = "type") Integer type,
                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        Page page = productAttributeService.propertyList(cid, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 添加属性
     * @param productAttribute
     * @return
     */
    @ApiOperation(value = "添加属性")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody ProductAttribute productAttribute){
        boolean save = productAttributeService.saveCreate(productAttribute);
        if(save){
            // 添加成功
            return CommonResult.success(save);
        }else{
            // 添加失败
            return  CommonResult.failed();
        }
    }
    /**
     * 编辑属性
     * @param productAttribute
     * @return
     */
    @ApiOperation(value = "编辑属性")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updata(@PathVariable(value = "id") Integer id,@RequestBody ProductAttribute productAttribute){
        boolean save = productAttributeService.updateById(productAttribute);
        if(save){
            // 添加成功
            return CommonResult.success(save);
        }else{
            // 添加失败
            return  CommonResult.failed();
        }
    }

    /**
     * 查看属性
     * @param id
     * @return
     */
    @ApiOperation(value = "查看属性")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult show(@PathVariable(value = "id") Integer id){
        ProductAttribute byId = productAttributeService.getById(id);
        return CommonResult.success(byId);
    }

    /**
     * 删除属性
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids){
        boolean b = productAttributeService.deleteLists(ids);
        if(b){
            // 删除成功
            return CommonResult.success(b);
        }else{
            // 删除失败
            return  CommonResult.failed();
        }
    }

    /**
     * 查看筛选属性
     * @param id
     * @return
     */
    @ApiOperation(value = "查看筛选属性")
    @RequestMapping(value = "/attrInfo/{id}", method = RequestMethod.GET)
    public CommonResult attrInfo(@PathVariable(value = "id") Long id){
      List<RelationAttrInfoDTO> list = productAttributeService.getProductCategoryById(id);
        return CommonResult.success(list);
    }

}
