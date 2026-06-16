package com.dataplatform.controller;

import com.dataplatform.common.Result;
import com.dataplatform.model.ProductStat;
import com.dataplatform.service.ProductStatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品统计", description = "商品销售统计查询接口")
@RestController
@RequestMapping("/api/product/stat")
public class ProductStatController {

    private final ProductStatService productStatService;

    public ProductStatController(ProductStatService productStatService) {
        this.productStatService = productStatService;
    }

    @Operation(summary = "热销商品排行", description = "按类目查询销量最高的商品")
    @GetMapping("/top")
    public Result<List<ProductStat>> findTopByCategory(@RequestParam String category,
                                                        @RequestParam(defaultValue = "5") Integer limit) {
        List<ProductStat> list = productStatService.findTopByCategory(category, limit);
        return Result.success(list);
    }
}
