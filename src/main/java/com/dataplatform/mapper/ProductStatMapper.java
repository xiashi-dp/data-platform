package com.dataplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dataplatform.model.ProductStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductStatMapper extends BaseMapper<ProductStat> {

    List<ProductStat> findTopByCategory(@Param("category") String category,
                                        @Param("limit") Integer limit);
}
