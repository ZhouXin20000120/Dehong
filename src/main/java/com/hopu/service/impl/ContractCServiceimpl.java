package com.hopu.service.impl;

import com.hopu.entity.ContractC;
import com.hopu.mapper.ContractCMapper;
import com.hopu.service.ContractCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContractCServiceimpl implements ContractCService {

    @Autowired
    ContractCMapper contractCMapper;

    @Override
    public List<ContractCMapper> findtract() {
        return this.contractCMapper.findtract();
    }

    @Override
    public int addcontract(ContractC entity) {
        entity.setContract_Id(UUID.randomUUID().toString());
        entity.setState(0);
        return this.contractCMapper.addcontract(entity);
    }

    @Override
    public int updatecontract(ContractC entity) {
        return this.contractCMapper.updatecontract(entity);
    }

    @Override
    public ContractC findbycid(String contract_Id) {
        return this.contractCMapper.findbycid(contract_Id);
    }

    @Override
    public int deletebycid(String contract_Id) {
        return this.contractCMapper.deletebycid(contract_Id);
    }

    @Override
    public int updateconstate(ContractC entity) {
        return this.contractCMapper.updateconstate(entity);
    }

    @Override
    public List<ContractC> selectnumber() {


        return this.contractCMapper.selectnumber();
    }
}
