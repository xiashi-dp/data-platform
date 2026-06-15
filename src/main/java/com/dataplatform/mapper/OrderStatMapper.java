package com.dataplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dataplatform.model.OrderStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderStatMapper extends BaseMapper<OrderStat> {

    List<OrderStat> findByDateRange(@Param("startDate") String startDate,
                                    @Param("endDate") String endDate,
                                    @Param("status") Integer status);
}
