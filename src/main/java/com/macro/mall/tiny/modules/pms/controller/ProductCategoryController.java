package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.ProductAttributeCategoryDTO;
import com.macro.mall.tiny.dto.ProductCateChildrenDTO;
import com.macro.mall.tiny.dto.ProductCategoryDTO;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.macro.mall.tiny.modules.pms.service.ProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * 列表获取
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "列表数据获取")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductCategory>> getList(@PathVariable Long parentId,
                                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        Page list = productCategoryService.list(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 是否显示导航栏
     * @param ids
     * @param navStatus
     * @return
     */
    @ApiOperation(value = "是否显示导航栏")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    public CommonResult updataNavStatus(@RequestParam(value = "ids", defaultValue = "1") Integer ids,
                                        @RequestParam(value = "navStatus", defaultValue = "10") Integer navStatus){
        boolean b = productCategoryService.navStatus(ids, navStatus);
        if(b){
            //修改成功
            return CommonResult.success(b);
        }else{
            //修改失败
            return CommonResult.failed();
        }
    }

    /**
     * 是否显示
     * @param ids
     * @param showStatus
     * @return
     */
    @ApiOperation(value = "是否显示")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updataShowStatus(@RequestParam(value = "ids", defaultValue = "1") Integer ids,
                                        @RequestParam(value = "showStatus", defaultValue = "10") Integer showStatus){
        boolean b = productCategoryService.showStatus(ids, showStatus);
        if(b){
            //修改成功
            return CommonResult.success(b);
        }else{
            //修改失败
            return CommonResult.failed();
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult updataShowStatus(@PathVariable("id") Integer id){
        boolean b = productCategoryService.removeById(id);
        if(b){
            //修改成功
            return CommonResult.success(b);
        }else{
            //修改失败
            return CommonResult.failed();
        }
    }

    /**
     * 新增
     * @param productCategoryDTO
     * @return
     */
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody ProductCategoryDTO productCategoryDTO){
        boolean b = productCategoryService.createSave(productCategoryDTO);
        if(b){
            //新增成功
            return CommonResult.success(b);
        }else{
            //新增失败
            return CommonResult.failed();
        }
    }

    /**
     * 编辑
     * @param productCategoryDTO
     * @return
     */
    @ApiOperation(value = "编辑")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id") Integer id,@RequestBody ProductCategoryDTO productCategoryDTO){
        boolean b = productCategoryService.updateSave(productCategoryDTO);
        if(b){
            //新增成功
            return CommonResult.success(b);
        }else{
            //新增失败
            return CommonResult.failed();
        }
    }

    /**
     * 查看
     * @param id
     * @return
     */
    @ApiOperation(value = "查看")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<ProductCategory> show(@PathVariable(value = "id") Integer id){
        ProductCategory byId = productCategoryService.getById(id);
        return CommonResult.success(byId);
    }

    /**
     * 筛选属性列表
     * @return
     */
    @ApiOperation(value = "筛选属性列表")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    public CommonResult listWithAttr(){
        List<ProductCateChildrenDTO> list = productCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}

