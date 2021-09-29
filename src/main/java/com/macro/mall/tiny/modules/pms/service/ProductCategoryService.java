package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.dto.ProductCateChildrenDTO;
import com.macro.mall.tiny.dto.ProductCategoryDTO;
import com.macro.mall.tiny.modules.pms.model.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
public interface ProductCategoryService extends IService<ProductCategory> {


    Page list(Long parentId, Integer pageNum, Integer pageSize);

    boolean navStatus(Integer ids, Integer navStatus);

    boolean showStatus(Integer ids, Integer showStatus);

    Page list(Integer pageNum, Integer pageSize);

    boolean createSave(ProductCategoryDTO productCategoryDTO);

    boolean updateSave(ProductCategoryDTO productCategoryDTO);

    List<ProductCateChildrenDTO> getListWithAttr();
}
