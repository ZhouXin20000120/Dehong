package com.hopu.service;

import com.hopu.entity.Export;

import java.util.List;

public interface ExportService {

    /**
     * 查询所有
     */
    List<Export> findexport();

    /**
     * 添加
     */
    int addexport(Export entity);


    /**
     * 查询单条
     */
    Export findbyexport(String EXPORT_PRODUCT_ID);


    /**
     * 修改
     */
    int updateexport(Export entity);

    /**
     * 删除
     */
    int deleteexport(String EXPORT_PRODUCT_ID);
}
