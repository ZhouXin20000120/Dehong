package com.hopu.mapper;

import com.hopu.entity.Export;

import java.util.List;

public interface ExportMapper {

    /**
     * 查询所有
     */
    List<Export> findexport();

    /**
     * 查询单条
     */
    Export findbyexport(String EXPORT_PRODUCT_ID);

    /**
     * 添加
     */
    int addexport(Export entity);


    /**
     * 修改
     */
    int updateexport(Export entity);

    /**
     * 删除
     */
    int deleteexport(String EXPORT_PRODUCT_ID);

}
