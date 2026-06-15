package com.dataplatform.controller;

import com.dataplatform.common.Result;
import com.dataplatform.model.VideoPv;
import com.dataplatform.service.VideoPvService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoPvController {

    private final VideoPvService videoPvService;

    public VideoPvController(VideoPvService videoPvService) {
        this.videoPvService = videoPvService;
    }

    @GetMapping("/pv")
    public Result<List<VideoPv>> getTopPv(@RequestParam(defaultValue = "20") int limit,
                                           @RequestParam(required = false) String startDate,
                                           @RequestParam(required = false) String endDate) {
        List<VideoPv> list = videoPvService.getTopPv(limit, startDate, endDate);
        return Result.success(list);
    }
}
