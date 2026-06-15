package com.dataplatform.model;

import java.time.LocalDate;

/**
 * 运营报表 —— 对应 Hive/Spark 处理后写入 MySQL 的日聚合数据
 */
public class OperationReport {
    private Long id;
    private LocalDate reportDate;        // 数据日期
    private Long newUserCount;           // 新增用户数
    private Long activeUserCount;        // 活跃用户数
    private Long totalOrderCount;        // 总订单数
    private Double totalOrderAmount;     // 总订单金额
    private Double avgOrderAmount;       // 平均订单金额
    private Long totalProductCount;      // 上架商品总数
    private Long newProductCount;        // 新增商品数
    private LocalDate createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }

    public Long getNewUserCount() { return newUserCount; }
    public void setNewUserCount(Long newUserCount) { this.newUserCount = newUserCount; }

    public Long getActiveUserCount() { return activeUserCount; }
    public void setActiveUserCount(Long activeUserCount) { this.activeUserCount = activeUserCount; }

    public Long getTotalOrderCount() { return totalOrderCount; }
    public void setTotalOrderCount(Long totalOrderCount) { this.totalOrderCount = totalOrderCount; }

    public Double getTotalOrderAmount() { return totalOrderAmount; }
    public void setTotalOrderAmount(Double totalOrderAmount) { this.totalOrderAmount = totalOrderAmount; }

    public Double getAvgOrderAmount() { return avgOrderAmount; }
    public void setAvgOrderAmount(Double avgOrderAmount) { this.avgOrderAmount = avgOrderAmount; }

    public Long getTotalProductCount() { return totalProductCount; }
    public void setTotalProductCount(Long totalProductCount) { this.totalProductCount = totalProductCount; }

    public Long getNewProductCount() { return newProductCount; }
    public void setNewProductCount(Long newProductCount) { this.newProductCount = newProductCount; }

    public LocalDate getCreateTime() { return createTime; }
    public void setCreateTime(LocalDate createTime) { this.createTime = createTime; }
}