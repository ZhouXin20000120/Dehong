package com.hopu.service;

import com.hopu.entity.ExtCproduct;

import java.util.List;

public interface ExtCproductService {

    /**
     * 查询所有
     */

    List<ExtCproduct> findextpro(String CONTRACT_PRODUCT_ID);

    /**
     * 添加
     */
    int addextpro(ExtCproduct entity);

    /**
     * 回显
     */
    ExtCproduct findbyextpro(ExtCproduct entity);

    /**
     * 修改
     */
    int updateextpro(ExtCproduct entity);

    /**
     * 删除
     */
    int deleteextpro(String EXT_CPRODUCT_ID);

    /**
     * 级联删除
     */
    int deleteextproandcontract(String CONTRACT_PRODUCT_ID);
}
