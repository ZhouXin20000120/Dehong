package com.hopu.controller;

import com.hopu.entity.*;
import com.hopu.service.ContractproService;
import com.hopu.service.ExtCproductService;
import com.hopu.service.Factory_cService;
import com.hopu.service.SysCodeBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class ExtCproductController {

    @Autowired
    ExtCproductService extCproductService;

    @Autowired
    Factory_cService factory_cService;

    @Autowired
    SysCodeBService sysCodeBService;

    @Autowired
    ContractproService contractproService;

    /**
     * 查询所有
     *
     */

    @RequestMapping("findextpro.action")
    public String findextpro(HttpServletRequest request,String CONTRACT_PRODUCT_ID){

        request.getSession().setAttribute("contract_Product_Idd",CONTRACT_PRODUCT_ID);
        List<ExtCproduct> list = extCproductService.findextpro(CONTRACT_PRODUCT_ID);

        request.getSession().setAttribute("ExtCproductlist",list);
        List<Factory_c> list2 = factory_cService.findfactoryAll();
        request.getSession().setAttribute("factoryList",list2);

        List<SysCodeB> list3 = sysCodeBService.findsyscode();
        request.getSession().setAttribute("ctypeList",list3);

        return "basicinfo/extCproductC/jExtCproductCreate";
    }

    /**
     * 添加
     */
    @RequestMapping("addextpro.action")
    public String addextpro(HttpServletRequest request,ExtCproduct entity){

        String contract_Product_Idd = (String) request.getSession().getAttribute("contract_Product_Idd");

        entity.setEXT_CPRODUCT_ID(UUID.randomUUID().toString());

        entity.setAmount(entity.getCnumber() * entity.getPrice());

        entity.setCONTRACT_PRODUCT_ID(contract_Product_Idd);

        System.out.println(entity+"------------------------------");
        extCproductService.addextpro(entity);

        return "redirect:findextpro.action?CONTRACT_PRODUCT_ID="+contract_Product_Idd+"";
    }

    /**
     * 回显
     */

    @RequestMapping("findbyextpro.action")
    public String findbyextpro(HttpServletRequest request,ExtCproduct entity){

        List<SysCodeB> list3 = sysCodeBService.findsyscode();
        request.getSession().setAttribute("ctypeListt",list3);


        List<Factory_c> list2 = factory_cService.findfactoryAll();
        request.getSession().setAttribute("factoryListt",list2);

        ExtCproduct exCproduct = extCproductService.findbyextpro(entity);


        request.getSession().setAttribute("obj",exCproduct);
        return "basicinfo/extCproductC/jExtCproductUpdate";
    }

    /**
     * 修改
     */
    @RequestMapping("updateextpro.action")
    public String updateextpro(HttpServletRequest request,ExtCproduct entity){
        String contract_Product_Idd = (String) request.getSession().getAttribute("contract_Product_Idd");

        entity.setAmount(entity.getCnumber() * entity.getPrice());

        System.out.println(entity+"==============================");

        extCproductService.updateextpro(entity);
        return "redirect:findextpro.action?CONTRACT_PRODUCT_ID="+contract_Product_Idd+"";
    }

    @RequestMapping("deleteByextproId.action")
    public String deleteByextproId(HttpServletRequest request,String EXT_CPRODUCT_ID){
        String contract_Product_Idd = (String) request.getSession().getAttribute("contract_Product_Idd");


            int i =  extCproductService.deleteextpro(EXT_CPRODUCT_ID);




        return "redirect:findextpro.action?CONTRACT_PRODUCT_ID="+contract_Product_Idd+"";
    }



}
