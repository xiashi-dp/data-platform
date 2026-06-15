package com.dataplatform.service;

import com.dataplatform.model.OrderStat;
import com.dataplatform.mapper.OrderStatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatService {

    private final OrderStatMapper orderStatMapper;

    public OrderStatService(OrderStatMapper orderStatMapper) {
        this.orderStatMapper = orderStatMapper;
    }

    public List<OrderStat> findByDateRange(String startDate, String endDate, Integer status) {
        return orderStatMapper.findByDateRange(startDate, endDate, status);
    }
}
