package com.hopu.controller;

import com.hopu.entity.ContractC;
import com.hopu.mapper.ContractCMapper;
import com.hopu.service.ContractCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ContractCController {

    @Autowired
    ContractCService contractCService;

    @RequestMapping("/findtract.action")
    public String findtract(HttpServletRequest request){

        List<ContractC> list2 = contractCService.selectnumber();

        request.getSession().setAttribute("contractlist",list2);


        return "basicinfo/contract/jContractList";
    }

    @RequestMapping("/addtract.action")
    public String addtract(){


        return "basicinfo/contract/jContractCreate";
    }

    @RequestMapping("/addcontract.action")
    public String addcontract(HttpServletRequest request, ContractC entity){



        int i = contractCService.addcontract(entity);

        if (i>0){
            return "redirect:findtract.action";
        }else{

            return "失败";
        }

    }

    @RequestMapping("/findbycid.action")
    public String findbycid(HttpServletRequest request, String contract_Id){


        ContractC contractC = contractCService.findbycid(contract_Id);
        request.getSession().setAttribute("con",contractC);

        return "basicinfo/contract/jContractUpdate";


    }

    @RequestMapping("/updatecontract.action")
    public String updatecontract(ContractC entity){



        int i = contractCService.updatecontract(entity);

        if (i>0){
            return "redirect:findtract.action";
        }else{

            return "失败";
        }

    }

    /**
     * 批量删除
     */
    @RequestMapping("/deletebycid.action")
    public String deletebycid(String [] contract_Id){

        for (int i = 0; i < contract_Id.length; i++) {
            contractCService.deletebycid(contract_Id[i]);
        }


            return "redirect:findtract.action";


    }

    /**
     * 修改状态
     */
    @RequestMapping("/updateconstate.action")
    public String updateconstate(String [] contract_Id,ContractC entity){

        for (String s : contract_Id) {
                entity.setContract_Id(s);
                entity.setState(1);
                contractCService.updateconstate(entity);

        }


        return "redirect:findtract.action";


    }


    @RequestMapping("/updateconstatee.action")
    public String updateconstatee(String [] contract_Id,ContractC entity){

        for (String s : contract_Id) {


                entity.setState(0);
                entity.setContract_Id(s);
                contractCService.updateconstate(entity);

        }


        return "redirect:findtract.action";


    }

}
