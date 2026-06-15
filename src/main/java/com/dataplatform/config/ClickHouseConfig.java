package com.dataplatform.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Properties;

@Configuration
public class ClickHouseConfig {

    @Bean
    public JdbcTemplate clickHouseJdbcTemplate() throws Exception {
        String url = "jdbc:clickhouse://localhost:8123/flink_db?compress=0";
        Properties props = new Properties();
        props.setProperty("user", "default");
        props.setProperty("password", "123456");
        ClickHouseDataSource dataSource = new ClickHouseDataSource(url, props);
        return new JdbcTemplate(dataSource);
    }
}
