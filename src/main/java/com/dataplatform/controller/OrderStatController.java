package com.dataplatform.controller;

import com.dataplatform.common.Result;
import com.dataplatform.model.OrderStat;
import com.dataplatform.service.OrderStatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order/stat")
public class OrderStatController {

    private final OrderStatService orderStatService;

    public OrderStatController(OrderStatService orderStatService) {
        this.orderStatService = orderStatService;
    }

    @GetMapping
    public Result<List<OrderStat>> findByDateRange(@RequestParam String startDate,
                                                    @RequestParam String endDate,
                                                    @RequestParam(required = false) Integer status) {
        List<OrderStat> list = orderStatService.findByDateRange(startDate, endDate, status);
        return Result.success(list);
    }
}
