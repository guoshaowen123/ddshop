package com.dd.shop.manager.dao;

import com.dd.shop.manager.pojo.po.Orderinfo;
import com.dd.shop.manager.pojo.po.OrderinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderinfoMapper {
    int countByExample(OrderinfoExample example);

    int deleteByExample(OrderinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orderinfo record);

    int insertSelective(Orderinfo record);

    List<Orderinfo> selectByExampleWithBLOBs(OrderinfoExample example);

    List<Orderinfo> selectByExample(OrderinfoExample example);

    Orderinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orderinfo record, @Param("example") OrderinfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Orderinfo record, @Param("example") OrderinfoExample example);

    int updateByExample(@Param("record") Orderinfo record, @Param("example") OrderinfoExample example);

    int updateByPrimaryKeySelective(Orderinfo record);

    int updateByPrimaryKeyWithBLOBs(Orderinfo record);

    int updateByPrimaryKey(Orderinfo record);
}