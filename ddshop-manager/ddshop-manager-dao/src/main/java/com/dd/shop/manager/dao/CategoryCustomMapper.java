package com.dd.shop.manager.dao;


import com.dd.shop.manager.pojo.po.Category;


import java.util.List;
import java.util.Map;

/**
 * Created by shencai on 2018/9/4.
 */
public interface CategoryCustomMapper {
    Long count(Map<String,Object> map);
    List<Category> listCategoryByPage(Map<String,Object> map);


}
