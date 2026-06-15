package com.dataplatform.service;

import com.dataplatform.model.ProductStat;
import com.dataplatform.mapper.ProductStatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatService {

    private final ProductStatMapper productStatMapper;

    public ProductStatService(ProductStatMapper productStatMapper) {
        this.productStatMapper = productStatMapper;
    }

    public List<ProductStat> findTopByCategory(String category, Integer limit) {
        return productStatMapper.findTopByCategory(category, limit);
    }
}
