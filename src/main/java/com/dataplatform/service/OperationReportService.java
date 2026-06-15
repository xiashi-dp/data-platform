package com.dataplatform.service;

import com.dataplatform.model.OperationReport;
import com.dataplatform.mapper.OperationReportMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OperationReportService {

    private final OperationReportMapper operationReportMapper;

    public OperationReportService(OperationReportMapper operationReportMapper) {
        this.operationReportMapper = operationReportMapper;
    }

    public List<OperationReport> findByDateRange(String startDate, String endDate) {
        return operationReportMapper.findByDateRange(startDate, endDate);
    }

}
