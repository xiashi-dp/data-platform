package com.dataplatform.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单统计 —— 订单维度数据
 */
public class OrderStat {
    private Long id;
    private String orderNo;            // 订单编号
    private Long userId;
    private String userName;
    private Double orderAmount;        // 订单金额
    private Integer orderStatus;       // 0: 待支付, 1: 已支付, 2: 已发货, 3: 已完成, -1: 已取消
    private String paymentMethod;      // 支付方式
    private LocalDate orderDate;       // 下单日期
    private LocalDateTime createTime;  // 下单时间
    private LocalDateTime payTime;     // 支付时间
    private String sourceChannel;      // 来源渠道

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Double getOrderAmount() { return orderAmount; }
    public void setOrderAmount(Double orderAmount) { this.orderAmount = orderAmount; }

    public Integer getOrderStatus() { return orderStatus; }
    public void setOrderStatus(Integer orderStatus) { this.orderStatus = orderStatus; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public LocalDateTime getPayTime() { return payTime; }
    public void setPayTime(LocalDateTime payTime) { this.payTime = payTime; }

    public String getSourceChannel() { return sourceChannel; }
    public void setSourceChannel(String sourceChannel) { this.sourceChannel = sourceChannel; }
}
