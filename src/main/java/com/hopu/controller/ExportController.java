package com.hopu.controller;

import com.hopu.entity.Export;
import com.hopu.entity.Factory_c;
import com.hopu.service.ExportService;
import com.hopu.service.Factory_cService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class ExportController {
    @Autowired
    ExportService exportService;

    @Autowired
    Factory_cService factory_cService;

    /**
     * 查询所有
     */
    @RequestMapping("findexport.action")
    public String findexport(HttpServletRequest request){
        List<Export> list = exportService.findexport();
        List<Factory_c> list1 = factory_cService.findfactoryAll();
        request.getSession().setAttribute("factoryList",list1);

        request.getSession().setAttribute("exportList",list);

        return "basicinfo/export/jExportList";
    }


    /**
     * 添加
     */
    @RequestMapping("addexport.action")
    public String addexport(Export entity){
        entity.setEXPORT_PRODUCT_ID(UUID.randomUUID().toString());
        exportService.addexport(entity);
        return "redirect:findexport.action";
    }

    /**
     * 根据id查询
     */
    @RequestMapping("findbyexport.action")
    public String findbyexport(String EXPORT_PRODUCT_ID,HttpServletRequest request){
        List<Factory_c> list1 = factory_cService.findfactoryAll();
        System.out.println(list1.toString());
        request.getSession().setAttribute("factoryList",list1);
        Export export = exportService.findbyexport(EXPORT_PRODUCT_ID);
        System.out.println(export.toString());
        request.getSession().setAttribute("export",export);
        return "basicinfo/export/jExportUpdate";
    }


    /**
     * 添加
     */
    @RequestMapping("updateexport.action")
    public String updateexport(Export entity){
        exportService.updateexport(entity);
        return "redirect:findexport.action";
    }


    /**
     * 删除
     */
    @RequestMapping("deleteexport.action")
    public String deleteexport(String EXPORT_PRODUCT_ID){

        exportService.deleteexport(EXPORT_PRODUCT_ID);
        return "redirect:findexport.action";
    }
}
