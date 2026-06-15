package com.dataplatform.model;

import java.sql.Timestamp;

/**
 * 视频 PV/UV 统计 —— 来自 ClickHouse 实时计算
 */
public class VideoPv {
    private String videoId;
    private Long pv;
    private Long uv;
    private Timestamp windowEnd;

    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }

    public Long getPv() { return pv; }
    public void setPv(Long pv) { this.pv = pv; }

    public Long getUv() { return uv; }
    public void setUv(Long uv) { this.uv = uv; }

    public Timestamp getWindowEnd() { return windowEnd; }
    public void setWindowEnd(Timestamp windowEnd) { this.windowEnd = windowEnd; }
}
