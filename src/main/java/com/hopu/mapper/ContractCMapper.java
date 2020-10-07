package com.hopu.mapper;

import com.hopu.entity.ContractC;

import java.util.List;

public interface ContractCMapper {

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
     * 根据id查询
     */
    ContractC findbycid(String contract_Id);

    /**
     * 修改
     */
    int updatecontract(ContractC entity);

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
     */
    List<ContractC> selectnumber();
}
