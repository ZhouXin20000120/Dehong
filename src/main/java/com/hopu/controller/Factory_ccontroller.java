package com.hopu.controller;

import com.hopu.entity.Factory_c;
import com.hopu.service.Factory_cService;
import com.hopu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class Factory_ccontroller {
    @Autowired
    Factory_cService factory_cService;


    /**
     * 查询所有工厂
     * @return
     */
    @RequestMapping("/findoryAlll.action")
    public String findoryAlll(HttpServletRequest request){
        List<Factory_c> list = factory_cService.findfactoryAll();
        request.getSession().setAttribute("dataList",list);
        return "basicinfo/factory/jFactoryList";

    }

    /**
     * 添加
     */

    @RequestMapping("/addory.action")
    public String addory(){
        return "basicinfo/factory/jFactoryCreate";
    }



    @RequestMapping("/addoryy.action")
    public String addoryy(Factory_c entity){
        String uuid = String.valueOf(UUID.randomUUID());
        entity.setFactory_id(uuid);
        int i = factory_cService.addfactory_c(entity);
        if (i>0){
            return "redirect:findoryAlll.action";
        }
        return "添加失败";

    }

    /**
     * 添加
     */

    @RequestMapping("/findorybyidd.action")
    public String findorybyidd(Factory_c entity,HttpServletRequest request){

        Factory_c factory_c = factory_cService.findbyfactory_id(entity);
        if (factory_c != null){
            request.getSession().setAttribute("obj",factory_c);
            return "basicinfo/factory/jFactoryUpdate";
        }
        return "查询失败";

    }

    /**
     * 修改
     */

    @RequestMapping("/updateoryy.action")
    public String updateoryy(Factory_c entity){
        int i = factory_cService.updatefactory_c(entity);
        if (i>0){
            return "redirect:findoryAlll.action";
        }
        return "失败";

    }


    @RequestMapping("/updatefaastate.action")
    public String updatefaastate(Factory_c entity,String [] factory_id){
        for (String s : factory_id) {
            entity.setState("2");
            entity.setFactory_id(s);
            factory_cService.updateastate(entity);
        }
            return "redirect:findoryAlll.action";


    }


    @RequestMapping("/updatefaastatee.action")
    public String updatefaastatee(Factory_c entity,String [] factory_id){
        for (String s : factory_id) {
            entity.setState("1");
            entity.setFactory_id(s);
            factory_cService.updateastate(entity);
        }

            return "redirect:findoryAlll.action";


    }

    @RequestMapping("/updateastate.action")
    public String updatefaastate(Factory_c entity){
        int i = factory_cService.updateastate(entity);
        if (i>0){
            return "redirect:findoryAlll.action";
        }
        return "失败";

    }


    @RequestMapping("/deletebyid.action")
    public String deletebyid(String [] factory_id){
        for (int i = 0; i < factory_id.length; i++) {
            factory_cService.deletebyid(factory_id[i]);

        }


        return "redirect:findoryAlll.action";

    }

    @RequestMapping("/deletedt.action")
    public String deletedt(String factory_id){
            int i = factory_cService.deletebyid(factory_id);
            if (i>0){
                return "redirect:findoryAlll.action";
            }

        return "失败";

    }


}
