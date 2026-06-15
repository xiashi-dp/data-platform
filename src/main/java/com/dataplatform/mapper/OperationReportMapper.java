package com.dataplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dataplatform.model.OperationReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationReportMapper extends BaseMapper<OperationReport> {
    List<OperationReport> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
}

