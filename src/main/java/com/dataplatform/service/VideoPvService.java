package com.dataplatform.service;

import com.dataplatform.model.VideoPv;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoPvService {

    @Resource(name = "clickHouseJdbcTemplate")
    private JdbcTemplate clickHouseJdbcTemplate;

    public List<VideoPv> getTopPv(int limit, String startDate, String endDate) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT video_id, sum(pv) AS pv, sum(uv) AS uv, max(window_end) AS last_time ")
           .append("FROM video_pv WHERE 1=1 ");

        if (startDate != null && !startDate.isEmpty()) {
            if (!startDate.contains(" ")) {
                startDate = startDate + " 00:00:00";
            }
            sql.append("AND window_end >= '").append(startDate).append("' ");
        }
        if (endDate != null && !endDate.isEmpty()) {
            if (!endDate.contains(" ")) {
                endDate = endDate + " 23:59:59";
            }
            sql.append("AND window_end <= '").append(endDate).append("' ");
        }

        sql.append("GROUP BY video_id ORDER BY pv DESC LIMIT ").append(limit);

        return clickHouseJdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            VideoPv v = new VideoPv();
            v.setVideoId(rs.getString("video_id"));
            v.setPv(rs.getLong("pv"));
            v.setUv(rs.getLong("uv"));
            v.setWindowEnd(rs.getTimestamp("last_time"));
            return v;
        });
    }
}
