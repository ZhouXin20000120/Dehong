package com.hopu.mapper;

import com.hopu.entity.Factory_c;

import java.util.List;

public interface Factory_cMapper {
    /**
     * 查询所有
     */

    List<Factory_c> findfactoryAll();

    /**
     * 根据id查询
     */

    Factory_c findbyfactory_id(Factory_c entity);

    /**
     * 添加工厂
     */

    int addfactory_c(Factory_c entity);

    /**
     * 修改工厂
     */

    int updatefactory_c(Factory_c entity);

    int updateastate(Factory_c entity);


    int deletebyid(String factory_id);
}
