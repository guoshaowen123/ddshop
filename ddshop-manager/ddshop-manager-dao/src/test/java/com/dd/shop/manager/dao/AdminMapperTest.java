package com.dd.shop.manager.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by shencai on 2018/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao-test.xml")
public class AdminMapperTest {
@Autowired
private AdminMapper mapper;
    @Test
    public void testSelectByExample() throws Exception {
        System.out.println(mapper.selectByPrimaryKey(1).getAge());
    }
}