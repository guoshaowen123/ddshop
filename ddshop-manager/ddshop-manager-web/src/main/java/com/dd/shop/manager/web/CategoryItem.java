package com.dd.shop.manager.web;


import com.dd.shop.common.pojo.dto.CategoryResult;
import com.dd.shop.common.pojo.dto.PageInfo;
import com.dd.shop.manager.pojo.po.Category;
import com.dd.shop.manager.pojo.vo.CategoryQuery;
import com.dd.shop.manager.service.impl.CatagoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by shencai on 2018/9/4.
 */
@Controller
public class CategoryItem {
    //打印slf4j异常日志
    private Logger logger= (Logger) LoggerFactory.getLogger(this.getClass());

    //依赖注入service
    @Autowired
    private CatagoryService categoryService;

    //分页查询
@ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.GET)
    public CategoryResult<Category> listByPage(PageInfo page, CategoryQuery query){
    CategoryResult<Category> category=new  CategoryResult<>();
    try{
        category=categoryService.listCategory(page,query);
    }catch (Exception e){
        logger.error(e.getMessage(),e);
    }
        return category;
    }

    //数组，List
    @ResponseBody
    @RequestMapping(value = "/iteam/update",method = RequestMethod.POST)
    public Object updateCategoryByIds(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try {
            i = categoryService.updateCategory(ids);
        } catch (Exception e) {
            //通过logback将异常打印日志中,ConsoleAppender FileAppender
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    //添加
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int addCategory(Category category){
        int i=0;
        try{
            i=categoryService.addCategory(category);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }
    //上下架
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public int updateCategory(Category category){
        int i=0;
        try{
            i=categoryService.changCategory(category);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

}
