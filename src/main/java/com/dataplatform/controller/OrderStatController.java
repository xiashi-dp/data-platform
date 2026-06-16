package com.dataplatform.controller;

import com.dataplatform.common.Result;
import com.dataplatform.model.OrderStat;
import com.dataplatform.service.OrderStatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "订单统计", description = "订单数据查询接口")
@RestController
@RequestMapping("/api/order/stat")
public class OrderStatController {

    private final OrderStatService orderStatService;

    public OrderStatController(OrderStatService orderStatService) {
        this.orderStatService = orderStatService;
    }

    @Operation(summary = "按日期范围查询", description = "查询指定日期范围内的订单数据，可按状态筛选")
    @GetMapping
    public Result<List<OrderStat>> findByDateRange(@RequestParam String startDate,
                                                    @RequestParam String endDate,
                                                    @RequestParam(required = false) Integer status) {
        List<OrderStat> list = orderStatService.findByDateRange(startDate, endDate, status);
        return Result.success(list);
    }
}
