package com.hopu.service.impl;

import com.hopu.entity.Factory_c;
import com.hopu.mapper.Factory_cMapper;
import com.hopu.service.Factory_cService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Factory_cServiceimpl implements Factory_cService {

    @Autowired
    Factory_cMapper factory_cMapper;

    @Override
    public List<Factory_c> findfactoryAll() {
        return factory_cMapper.findfactoryAll();
    }

    @Override
    public Factory_c findbyfactory_id(Factory_c entity) {
        return factory_cMapper.findbyfactory_id(entity);
    }

    @Override
    public int addfactory_c(Factory_c entity) {
        return factory_cMapper.addfactory_c(entity);
    }

    @Override
    public int updatefactory_c(Factory_c entity) {
        return factory_cMapper.updatefactory_c(entity);
    }

    @Override
    public int updateastate(Factory_c entity) {
        return factory_cMapper.updateastate(entity);
    }

    @Override
    public int deletebyid(String factory_id) {
        return factory_cMapper.deletebyid(factory_id);
    }
}
