package com.dataplatform.controller;

import com.dataplatform.common.Result;
import com.dataplatform.model.ProductStat;
import com.dataplatform.service.ProductStatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/stat")
public class ProductStatController {

    private final ProductStatService productStatService;

    public ProductStatController(ProductStatService productStatService) {
        this.productStatService = productStatService;
    }

    @GetMapping("/top")
    public Result<List<ProductStat>> findTopByCategory(@RequestParam String category,
                                                        @RequestParam(defaultValue = "5") Integer limit) {
        List<ProductStat> list = productStatService.findTopByCategory(category, limit);
        return Result.success(list);
    }
}
