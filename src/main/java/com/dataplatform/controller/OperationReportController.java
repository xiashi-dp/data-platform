package com.dataplatform.controller;

import com.dataplatform.model.OperationReport;
import com.dataplatform.service.OperationReportService;
import org.springframework.web.bind.annotation.*;
import com.dataplatform.common.Result;
import java.util.List;

@RestController
@RequestMapping("/api/report")

public class OperationReportController {
    private final OperationReportService operationReportService;

    public OperationReportController(OperationReportService operationReportService) {
        this.operationReportService = operationReportService;
    }
    @GetMapping("/range")
    public Result<List<OperationReport>>findByDateRange(@RequestParam String startDate,
                                       @RequestParam String endDate) {
        List<OperationReport> list = operationReportService.findByDateRange(startDate, endDate);
        return Result.success(list);
    }
}
