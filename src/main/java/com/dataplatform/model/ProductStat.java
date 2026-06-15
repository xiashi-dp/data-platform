package com.dataplatform.model;

import java.time.LocalDate;

/**
 * 商品统计 —— 每个商品在各维度的聚合数据
 */
public class ProductStat {
    private Long id;
    private Long productId;
    private String productName;
    private String category;          // 商品类目
    private LocalDate statDate;       // 统计日期
    private Long viewCount;           // 浏览量
    private Long saleCount;           // 销量
    private Double saleAmount;        // 销售额
    private Long stockCount;          // 库存
    private Integer status;           // 0: 下架, 1: 在售
    private LocalDate createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getStatDate() { return statDate; }
    public void setStatDate(LocalDate statDate) { this.statDate = statDate; }

    public Long getViewCount() { return viewCount; }
    public void setViewCount(Long viewCount) { this.viewCount = viewCount; }

    public Long getSaleCount() { return saleCount; }
    public void setSaleCount(Long saleCount) { this.saleCount = saleCount; }

    public Double getSaleAmount() { return saleAmount; }
    public void setSaleAmount(Double saleAmount) { this.saleAmount = saleAmount; }

    public Long getStockCount() { return stockCount; }
    public void setStockCount(Long stockCount) { this.stockCount = stockCount; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public LocalDate getCreateTime() { return createTime; }
    public void setCreateTime(LocalDate createTime) { this.createTime = createTime; }
}