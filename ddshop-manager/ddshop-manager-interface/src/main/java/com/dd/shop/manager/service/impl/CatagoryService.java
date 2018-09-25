package com.dd.shop.manager.service.impl;

import com.dd.shop.common.pojo.dto.CategoryResult;
import com.dd.shop.common.pojo.dto.PageInfo;
import com.dd.shop.manager.pojo.po.Category;
import com.dd.shop.manager.pojo.vo.CategoryQuery;

import java.util.List;

/**
 * Created by shencai on 2018/9/4.
 * 处理分类管理
 */
public interface CatagoryService  {

    CategoryResult<Category> listCategory(PageInfo page,CategoryQuery query);

    int updateCategory(List<Long> ids);
    int addCategory(Category category);

    int changCategory(Category category);
}
