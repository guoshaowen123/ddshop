package com.dd.shop.manager.service.impl;


import com.dd.shop.common.pojo.dto.CategoryResult;
import com.dd.shop.common.pojo.dto.PageInfo;
import com.dd.shop.manager.dao.CategoryCustomMapper;
import com.dd.shop.manager.dao.CategoryMapper;
import com.dd.shop.manager.pojo.po.Category;
import com.dd.shop.manager.pojo.po.CategoryExample;
import com.dd.shop.manager.pojo.vo.CategoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shencai on 2018/9/4.
 */
@Service
public class CatagoryServiceImpl implements  CatagoryService {
    //初始化logger
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    //依赖注入dao层接口
@Autowired
    private CategoryCustomMapper dao;
    @Autowired
    private CategoryMapper mapper;
    @Override
    public CategoryResult<Category> listCategory(PageInfo page,CategoryQuery query) {
        CategoryResult<Category> result=new CategoryResult<>();
        //查询正确的返回0,否则返回非0
        result.setCode(0);
        result.setMsg("success");
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("page",page);
            map.put("query",query);
            Long count=dao.count(map);
           List<Category> list= dao.listCategoryByPage(map);
             result.setCount(count);
            result.setData(list);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg("failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateCategory(List<Long> ids) {
        int i = 0;
        try {
            //封装一个商品对象，携带了删除状态
            Category record = new Category();
            record.setStatus((byte) 3);
            //使用example
            //创建模板
            CategoryExample example = new CategoryExample();
            CategoryExample.Criteria criteria = example.createCriteria();
            //设值
            criteria.andIdIn(ids);
            //真正执行修改操作
            i = mapper.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }
    //添加
    @Override
    public int addCategory(Category record) {
    int i=0;
        try {
            Date date=new Date();
            record.setCreatedate(date);
            record.setStatus((byte) 1);
            i=mapper.insert(record);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        };
        return i;
    }

    @Override
    public int changCategory(Category category) {
        int i=0;
        try {
            Date date=new Date();
            category.setModifytime(date);
            System.out.println(category.getStatus());
            int a=category.getStatus();
            if(a==1){
                category.setStatus((byte) 2);
            }else if (a==2){
                category.setStatus((byte) 1);
            }

            i=mapper.updateByPrimaryKeySelective(category);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        };
        return i;
    }
}
