-- 创建数据库
CREATE DATABASE IF NOT EXISTS data_platform DEFAULT CHARSET utf8mb4;

USE data_platform;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
    `id`         BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username`   VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    `password`   VARCHAR(255) NOT NULL COMMENT '密码',
    `real_name`  VARCHAR(50)  DEFAULT '' COMMENT '真实姓名',
    `email`      VARCHAR(100) DEFAULT '' COMMENT '邮箱',
    `phone`      VARCHAR(20)  DEFAULT '' COMMENT '手机号',
    `role`       VARCHAR(20)  DEFAULT 'VIEWER' COMMENT '角色: ADMIN/OPERATOR/VIEWER',
    `status`     INT          DEFAULT 1 COMMENT '1启用 0禁用',
    `create_time` DATETIME    DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT '用户表';

-- 运营报表（Hive/Spark 处理后写入的数据）
CREATE TABLE IF NOT EXISTS `operation_report` (
    `id`                BIGINT AUTO_INCREMENT PRIMARY KEY,
    `report_date`       DATE   NOT NULL COMMENT '数据日期',
    `new_user_count`    BIGINT DEFAULT 0 COMMENT '新增用户数',
    `active_user_count` BIGINT DEFAULT 0 COMMENT '活跃用户数',
    `total_order_count` BIGINT DEFAULT 0 COMMENT '总订单数',
    `total_order_amount` DECIMAL(15,2) DEFAULT 0.00 COMMENT '总订单金额',
    `avg_order_amount`  DECIMAL(10,2) DEFAULT 0.00 COMMENT '平均订单金额',
    `total_product_count` BIGINT DEFAULT 0 COMMENT '上架商品总数',
    `new_product_count`   BIGINT DEFAULT 0 COMMENT '新增商品数',
    `create_time`       DATE   DEFAULT (CURRENT_DATE),
    UNIQUE KEY `uk_report_date` (`report_date`)
) COMMENT '运营报表';

-- 商品统计
CREATE TABLE IF NOT EXISTS `product_stat` (
    `id`           BIGINT AUTO_INCREMENT PRIMARY KEY,
    `product_id`   BIGINT       NOT NULL COMMENT '商品ID',
    `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
    `category`     VARCHAR(100) DEFAULT '' COMMENT '商品类目',
    `stat_date`    DATE         NOT NULL COMMENT '统计日期',
    `view_count`   BIGINT       DEFAULT 0 COMMENT '浏览量',
    `sale_count`   BIGINT       DEFAULT 0 COMMENT '销量',
    `sale_amount`  DECIMAL(15,2) DEFAULT 0.00 COMMENT '销售额',
    `stock_count`  BIGINT       DEFAULT 0 COMMENT '库存',
    `status`       INT          DEFAULT 1 COMMENT '0下架 1在售',
    `create_time`  DATE         DEFAULT (CURRENT_DATE),
    UNIQUE KEY `uk_product_date` (`product_id`, `stat_date`)
) COMMENT '商品统计';

-- 订单统计
CREATE TABLE IF NOT EXISTS `order_stat` (
    `id`              BIGINT AUTO_INCREMENT PRIMARY KEY,
    `order_no`        VARCHAR(50)   NOT NULL COMMENT '订单编号',
    `user_id`         BIGINT        NOT NULL COMMENT '用户ID',
    `user_name`       VARCHAR(50)   DEFAULT '' COMMENT '用户名称',
    `order_amount`    DECIMAL(15,2) DEFAULT 0.00 COMMENT '订单金额',
    `order_status`    INT           DEFAULT 0 COMMENT '0待支付 1已支付 2已发货 3已完成 -1已取消',
    `payment_method`  VARCHAR(20)   DEFAULT '' COMMENT '支付方式',
    `order_date`      DATE          NOT NULL COMMENT '下单日期',
    `create_time`     DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    `pay_time`        DATETIME      DEFAULT NULL COMMENT '支付时间',
    `source_channel`  VARCHAR(50)   DEFAULT '' COMMENT '来源渠道',
    UNIQUE KEY `uk_order_no` (`order_no`)
) COMMENT '订单统计';

-- 插入一条默认管理员（密码 123456，后面可以改）
INSERT INTO `user` (`username`, `password`, `real_name`, `role`,
                    `status`)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员',
        'ADMIN', 1);

-- 运营报表测试数据
INSERT INTO `operation_report` (`report_date`, `new_user_count`, `active_user_count`,
                                `total_order_count`, `total_order_amount`, `avg_order_amount`,
                                `total_product_count`, `new_product_count`)
VALUES ('2026-06-01', 1200, 35000, 8900, 456000.00, 51.24, 15000, 230),
       ('2026-06-02', 980, 32800, 8200, 398000.00, 48.54, 15120, 180),
       ('2026-06-03', 1560, 37200, 9500, 523000.00, 55.05, 15200, 280),
       ('2026-06-04', 1100, 34100, 8700, 412000.00, 47.36, 15300, 200),
       ('2026-06-05', 1350, 36800, 9200, 489000.00, 53.15, 15380, 260),
       ('2026-06-06', 890, 31500, 7800, 367000.00, 47.05, 15450, 160),
       ('2026-06-07', 1680, 38900, 10100, 567000.00, 56.14, 15500, 310);

