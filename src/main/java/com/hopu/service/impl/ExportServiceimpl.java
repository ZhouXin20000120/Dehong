package com.hopu.service.impl;

import com.hopu.entity.Export;
import com.hopu.mapper.ExportMapper;
import com.hopu.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportServiceimpl implements ExportService {
    @Autowired
    ExportMapper exportMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Export> findexport() {
        return this.exportMapper.findexport();
    }

    @Override
    public int addexport(Export entity) {
        return this.exportMapper.addexport(entity);
    }

    @Override
    public Export findbyexport(String EXPORT_PRODUCT_ID) {
        return this.exportMapper.findbyexport(EXPORT_PRODUCT_ID);
    }

    @Override
    public int updateexport(Export entity) {
        return this.exportMapper.updateexport(entity);
    }

    @Override
    public int deleteexport(String EXPORT_PRODUCT_ID) {
        return this.exportMapper.deleteexport(EXPORT_PRODUCT_ID);
    }
}
