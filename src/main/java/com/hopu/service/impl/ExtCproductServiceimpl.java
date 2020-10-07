package com.hopu.service.impl;

import com.hopu.entity.ExtCproduct;
import com.hopu.mapper.ExtCproductMapper;
import com.hopu.service.ExtCproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExtCproductServiceimpl implements ExtCproductService {

    @Autowired
    ExtCproductMapper extCproductMapper;

    @Override
    public List<ExtCproduct> findextpro(String CONTRACT_PRODUCT_ID) {
        return this.extCproductMapper.findextpro(CONTRACT_PRODUCT_ID);
    }

    @Override
    public int addextpro(ExtCproduct entity) {
        return this.extCproductMapper.addextpro(entity);
    }

    @Override
    public ExtCproduct findbyextpro(ExtCproduct entity) {
        return this.extCproductMapper.findbyextpro(entity);
    }

    @Override
    public int updateextpro(ExtCproduct entity) {
        return this.extCproductMapper.updateextpro(entity);
    }

    @Override
    public int deleteextpro(String EXT_CPRODUCT_ID) {
        return this.extCproductMapper.deleteextpro(EXT_CPRODUCT_ID);
    }

    @Override
    public int deleteextproandcontract(String CONTRACT_PRODUCT_ID) {
        return this.extCproductMapper.deleteextproandcontract(CONTRACT_PRODUCT_ID);
    }
}
