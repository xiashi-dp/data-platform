package com.dataplatform.controller;

import com.dataplatform.common.Result;
import com.dataplatform.model.OperationReport;
import com.dataplatform.service.OperationReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "运营报表", description = "运营日报数据查询接口")
@RestController
@RequestMapping("/api/report")
public class OperationReportController {
    private final OperationReportService operationReportService;

    public OperationReportController(OperationReportService operationReportService) {
        this.operationReportService = operationReportService;
    }

    @Operation(summary = "按日期范围查询", description = "查询指定日期范围内的运营报表数据")
    @GetMapping("/range")
    public Result<List<OperationReport>> findByDateRange(@RequestParam String startDate,
                                                          @RequestParam String endDate) {
        List<OperationReport> list = operationReportService.findByDateRange(startDate, endDate);
        return Result.success(list);
    }
}
