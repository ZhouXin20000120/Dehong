package com.hopu.service.impl;

import com.hopu.entity.ContractPro;
import com.hopu.mapper.ContractProdutMapper;
import com.hopu.service.ContractproService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractproServiceimpl implements ContractproService {

    @Autowired
    ContractProdutMapper contractProdutMapper;

    @Override
    public List<ContractPro> findContractPro(String contract_Id) {
        return this.contractProdutMapper.findContractPro(contract_Id);
    }

    @Override
    public ContractPro findbyContractPro(String CONTRACT_PRODUCT_ID) {
        return this.contractProdutMapper.findbyContractPro(CONTRACT_PRODUCT_ID);
    }

    @Override
    public int addContractPro(ContractPro entity) {


        return this.contractProdutMapper.addContractPro(entity);
    }

    @Override
    public int updateContractPro(ContractPro entity) {
        return this.contractProdutMapper.updateContractPro(entity);
    }

    @Override
    public int deleteContractPro(String CONTRACT_PRODUCT_ID) {
        return this.contractProdutMapper.deleteContractPro(CONTRACT_PRODUCT_ID);
    }
}
