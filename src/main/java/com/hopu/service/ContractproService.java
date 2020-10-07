package com.hopu.service;

import com.hopu.entity.ContractPro;

import java.util.List;

public interface ContractproService {

    /**
     * 查询所有
     */
    List<ContractPro> findContractPro(String contract_Id);

    /**
     * 查询单条
     */
    ContractPro findbyContractPro(String CONTRACT_PRODUCT_ID);

    /**
     * 添加
     */
    int addContractPro(ContractPro entity);


    /**
     * 修改
     */
    int updateContractPro(ContractPro entity);

    /**
     * 删除
     */
    int deleteContractPro(String CONTRACT_PRODUCT_ID);
}
