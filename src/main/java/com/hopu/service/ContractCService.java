package com.hopu.service;

import com.hopu.entity.ContractC;
import com.hopu.mapper.ContractCMapper;

import java.util.List;

public interface ContractCService {

    /**
     * 查询购销合同所有
     * @return
     */
    List<ContractCMapper> findtract();

    /**
     * 添加
     */
    int addcontract(ContractC entity);

    /**
     * 修改
     */
    int updatecontract(ContractC entity);

    /**
     * 根据id查询
     */
    ContractC findbycid(String contract_Id);

    /**
     * 删除
     */
    int deletebycid(String contract_Id);

    /**
     * 修改
     */
    int updateconstate(ContractC entity);

    /**
     * 查询数量
     * @return
     */
    List<ContractC> selectnumber();
}
