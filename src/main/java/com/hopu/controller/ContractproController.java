package com.hopu.controller;

import com.hopu.entity.ContractC;
import com.hopu.entity.ContractPro;
import com.hopu.entity.Factory_c;
import com.hopu.service.ContractCService;
import com.hopu.service.ContractproService;
import com.hopu.service.ExtCproductService;
import com.hopu.service.Factory_cService;
import com.hopu.util.DownloadUtil;
import com.hopu.util.file.PoiUtil;
import com.hopu.util.UtilFuns;
import org.apache.catalina.util.ConcurrentDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Controller
public class ContractproController {
    @Autowired
    ContractproService contractproService;

    @Autowired
    Factory_cService factory_cService;

    @Autowired
    ExtCproductService extCproductService;

    @Autowired
    ContractCService contractCService;


    /**
     * 查询所有
     */
    @RequestMapping("findpro.action")
    public String findpro(HttpServletRequest request,String contract_Id){
        List<ContractPro> list = contractproService.findContractPro(contract_Id);
        List<Factory_c> list1 = factory_cService.findfactoryAll();
        request.getSession().setAttribute("factoryListpro",list1);

        request.getSession().setAttribute("contract_Idddd",contract_Id);
        request.getSession().setAttribute("contractProList",list);

        return "basicinfo/contractpro/jContractproList";
    }


    /**
     * 添加
     */
    @RequestMapping("addpro.action")
    public String addpro(ContractPro entity,HttpServletRequest request){
        entity.setCONTRACT_PRODUCT_ID(UUID.randomUUID().toString());
        String contract_idddd = (String) request.getSession().getAttribute("contract_Idddd");
        entity.setContract_Id(contract_idddd);

        BigDecimal cnumber = entity.getCnumber();
        BigDecimal price = entity.getPrice();


        BigDecimal result3 = cnumber.multiply(price);

        entity.setAmount(result3);
        System.out.println(entity.toString());
        contractproService.addContractPro(entity);
        return "redirect:findpro.action?contract_Id="+contract_idddd+"";
    }

    /**
     * 根据id查询
     */
    @RequestMapping("findbyContractPro.action")
    public String findbyContractPro(String CONTRACT_PRODUCT_ID,HttpServletRequest request){
        List<Factory_c> list1 = factory_cService.findfactoryAll();
        request.getSession().setAttribute("factoryListproo",list1);

        ContractPro contractPro = contractproService.findbyContractPro(CONTRACT_PRODUCT_ID);

        request.getSession().setAttribute("contractPro",contractPro);

        return "basicinfo/contractpro/jContractproUpdate";
    }


    /**
     * 添加
     */
    @RequestMapping("updateContractPro.action")
    public String updateContractPro(ContractPro entity,HttpServletRequest request){
        String contract_idddd = (String) request.getSession().getAttribute("contract_Idddd");
        contractproService.updateContractPro(entity);
        return "redirect:findpro.action?contract_Id="+contract_idddd+"";
    }


    /**
     * 删除
     */
    @RequestMapping("deleteContractPro.action")
    public String deleteContractPro(HttpServletRequest request,String CONTRACT_PRODUCT_ID){

        String contract_idddd = (String) request.getSession().getAttribute("contract_Idddd");

        extCproductService.deleteextproandcontract(CONTRACT_PRODUCT_ID);

        contractproService.deleteContractPro(CONTRACT_PRODUCT_ID);
        return "redirect:findpro.action?contract_Id="+contract_idddd+"";
    }



    /***
     * 打印功能
     * @param response
     * @param contract_Id
     * @throws IOException
     */
    @RequestMapping("/print.action")
    public void Printing(HttpServletResponse response, String contract_Id) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("出货表");

        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        List<ContractPro> classmateList = contractproService.findContractPro(contract_Id);
        String fileName = new Date() + ".xls";
        // 新增数据行，并且设置单元格数据
        int rowNum = 1;
        // headers表示excel表中第一行的表头 在excel表中添加表头
        String[] headers = {"客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款"};
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);

            cell.setCellStyle(headerStyle);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (ContractPro contractPro : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            if (contractPro.getContract_c().getCustom_Name()!=null) {
                row1.createCell(0).setCellValue(contractPro.getContract_c().getCustom_Name());
            }

            if (contractPro.getCONTRACT_PRODUCT_ID()!=null) {
                row1.createCell(1).setCellValue(contractPro.getCONTRACT_PRODUCT_ID());
            }

            if (contractPro.getProduct_No()!=null) {
                row1.createCell(2).setCellValue(contractPro.getProduct_No());
            }

            if (contractPro.getCnumber()!=null) {
                row1.createCell(3).setCellValue(contractPro.getCnumber().doubleValue());
            }

            if (contractPro.getFactory()!=null) {
                row1.createCell(4).setCellValue(contractPro.getFactory());
            }

            if (contractPro.getContract_c().getDelivery_Period()!=null) {
                row1.createCell(5).setCellValue(contractPro.getContract_c().getDelivery_Period());
            }

            if (contractPro.getContract_c().getShip_Time()!=null) {
                row1.createCell(6).setCellValue(ConcurrentDateFormat.formatRfc1123(contractPro.getContract_c().getShip_Time()));
            }

            if (contractPro.getOut_Number()!=null) {
                row1.createCell(7).setCellValue(contractPro.getOut_Number().doubleValue());
            }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }




    /***
     * 打印功能
     * @param response
     * @param contract_Id
     * @throws IOException
     */
    @RequestMapping("/printmoban.action")
    public void printmoban(HttpServletResponse response, String path,  String contract_Id) throws IOException, ParseException {

        ContractC contract = contractCService.findbycid(contract_Id);

        System.out.println(contract);
        //相同厂家的信息一起打印
        List<ContractPro> oList = contractproService.findContractPro(contract_Id);
        UtilFuns utilFuns = new UtilFuns();
        //String tempXlsFile = path + "make/xlsprint/tCONTRACT.xls";		//获取文件
        String tempXlsFile = path + "make/xlsprint/tCONTRACTVO.xls";	//获取模板文件

        //填写每页的内容，之后在循环每页读取打印
        Map<String,String> pageMap = null;
        List<Map> pageList = new ArrayList();			//打印页

        ContractPro oProduct = null;
        String stars = "";
        for(int j=0;j<contract.getImport_Num();j++){		//重要程度
            stars += "★";
        }

        String oldFactory = "";
        for(int i=0;i<oList.size();i++){
            oProduct = oList.get(i);	//获得货物
            pageMap = new HashMap();	//每页的内容

            pageMap.put("Offeror", "收 购 方：" + contract.getOfferor());
            pageMap.put("Factory", "生产工厂：" + oProduct.getFactory_c().getFactory_name());
            pageMap.put("ContractNo", "合 同 号：" + contract.getContract_No());
            pageMap.put("Contacts", "联 系 人：" + oProduct.getFactory_c().getContactor());
            pageMap.put("SigningDate", "签单日期："+ UtilFuns.formatDateTimeCN(UtilFuns.dateTimeFormat(contract.getSigning_Date())));
            pageMap.put("Phone", "电    话：" + UtilFuns.convertNull(oProduct.getFactory_c().getPhone()));
            pageMap.put("InputBy", "制单：" + contract.getInput_By());
            pageMap.put("CheckBy", "审单："+ utilFuns.fixSpaceStr(contract.getCheck_By(),26)+"验货员："+utilFuns.convertNull(contract.getInspector()));
            pageMap.put("Remark", "  "+UtilFuns.convertNull(contract.getRemark()));
            pageMap.put("Request", "  "+UtilFuns.convertNull(contract.getRequest()));

            pageMap.put("ProductImage", oProduct.getProduct_Image());
            pageMap.put("ProductDesc", oProduct.getProduct_Desc());
            pageMap.put("Cnumber", String.valueOf(oProduct.getCnumber().doubleValue()));
            if(oProduct.getPacking_Unit().equals("PCS")){
                pageMap.put("PackingUnit", "只");
            }else if(oProduct.getPacking_Unit().equals("SETS")){
                pageMap.put("PackingUnit", "套");
            }
            pageMap.put("Price", String.valueOf(oProduct.getPrice().doubleValue()));
            pageMap.put("ProductNo", oProduct.getProduct_No());

            oldFactory = oProduct.getFactory_c().getFactory_name();

            if(contract.getPrint_Style().equals("2")){
                i++;	//读取第二个货物信息
                if(i<oList.size()){
                    oProduct = oList.get(i);

                    if(oProduct.getFactory_c().getFactory_name().equals(oldFactory)){	//厂家不同另起新页打印，除去第一次的比较

                        pageMap.put("ProductImage2", oProduct.getProduct_Image());
                        pageMap.put("ProductDesc2", oProduct.getProduct_Desc());
                        pageMap.put("Cnumber2", String.valueOf(oProduct.getCnumber().doubleValue()));
                        if(oProduct.getPacking_Unit().equals("PCS")){
                            pageMap.put("PackingUnit2", "只");
                        }else if(oProduct.getPacking_Unit().equals("SETS")){
                            pageMap.put("PackingUnit2", "套");
                        }
                        pageMap.put("Price2", String.valueOf(oProduct.getPrice().doubleValue()));
                        //pageMap.put("Amount2", String.valueOf(oProduct.getAmount().doubleValue()));			//在excel中金额采用公式，所以无需准备数据
                        pageMap.put("ProductNo2", oProduct.getProduct_No());
                    }else{
                        i--;	//tip:list退回
                    }
                }else{
                    pageMap.put("ProductNo2", null);	//后面依据此判断是否有第二个货物
                }
            }

            pageMap.put("ContractDesc", stars+" 货物描述");			//重要程度 + 货物描述

            pageList.add(pageMap);
        }

        int cellHeight = 96;	//一个货物的高度			用户需求，一个货物按192高度打印，后来又嫌难看，打印高度和2款高度一样。
//		if(contract.getPrintStyle().equals("2")){
//			cellHeight = 96;	//两个货物的高度
//		}

        PoiUtil poiUtil = new PoiUtil();
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(tempXlsFile));	//打开excel文件
        HSSFFont defaultFont10 = poiUtil.defaultFont10(wb);		//设置字体
        HSSFFont defaultFont12 = poiUtil.defaultFont12(wb);		//设置字体
        HSSFFont blackFont = poiUtil.blackFont12(wb);			//设置字体
        Short rmb2Format = poiUtil.rmb2Format(wb);				//设置格式
        Short rmb4Format = poiUtil.rmb4Format(wb);				//设置格式


        HSSFSheet sheet = wb.getSheetAt(0);				//选择第一个工作簿
        wb.setSheetName(0, "购销合同");					//设置工作簿的名称


        //sheet.setDefaultColumnWidth((short) 20); 		// 设置每列默认宽度

//		POI分页符有BUG，必须在模板文件中插入一个分页符，然后再此处删除预设的分页符；最后在下面重新设置分页符。
//		sheet.setAutobreaks(false);
//		int iRowBreaks[] = sheet.getRowBreaks();
//		sheet.removeRowBreak(3);
//		sheet.removeRowBreak(4);
//		sheet.removeRowBreak(5);
//		sheet.removeRowBreak(6);

        CellRangeAddress region = null;
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();		//add picture

        HSSFRow nRow = null;
        HSSFCell nCell   = null;
        int curRow = 0;

        //打印每页
        Map<String,String> printMap = null;
        for(int p=0;p<pageList.size();p++){
            printMap = pageList.get(p);

            if(p>0){
                sheet.setRowBreak(curRow++);	//在第startRow行设置分页符
            }


            //设置logo图片
            poiUtil.setPicture(wb, patriarch, path+"make/xlsprint/logo.jpg", curRow, 2, curRow+4, 2);

            //header
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(21);

            nCell   = nRow.createCell((3));
            nCell.setCellValue("SHAANXI");
            nCell.setCellStyle(headStyle(wb));

            //header
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(41);

            nCell   = nRow.createCell((3));
            nCell.setCellValue("     DH INTERNATIONAL ");
            nCell.setCellStyle(tipStyle(wb));

            curRow++;

            //header
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(20);

            nCell   = nRow.createCell((1));
            nCell.setCellValue("                 西经济技术开发区西城一路27号无迪大厦19楼");
            nCell.setCellStyle(addressStyle(wb));

            //header
            nCell   = nRow.createCell((6));
            nCell.setCellValue(" CO., LTD.");
            nCell.setCellStyle(ltdStyle(wb));

            //header
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(15);

            nCell   = nRow.createCell((1));
            nCell.setCellValue("                   TEL: 0086-29-86339371  FAX: 0086-29-86303310               E-MAIL: hpadmin@qq.com");
            nCell.setCellStyle(telStyle(wb));

            //line
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(7);

            poiUtil.setLine(wb, patriarch, curRow, 2, curRow, 8);	//draw line

            //header
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(30);

            nCell   = nRow.createCell((4));
            nCell.setCellValue("    购   销   合   同");
            nCell.setCellStyle(titleStyle(wb));

            //Offeror
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(20);

            nCell   = nRow.createCell((1));
            nCell.setCellValue(printMap.get("Offeror"));
            nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));

            //Facotry
            nCell   = nRow.createCell((5));
            nCell.setCellValue(printMap.get("Factory"));
            nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));

            //ContractNo
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(20);

            nCell   = nRow.createCell(1);
            nCell.setCellValue(printMap.get("ContractNo"));
            nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));

            //Contacts
            nCell  = nRow.createCell(5);
            nCell.setCellValue(printMap.get("Contacts"));
            nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));

            //SigningDate
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(20);

            nCell = nRow.createCell(1);
            nCell.setCellValue(printMap.get("SigningDate"));
            nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));

            //Phone
            nCell = nRow.createCell(5);
            nCell.setCellValue(printMap.get("Phone"));
            nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));

            //importNum
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(24);

            region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(1);
            nCell.setCellValue("产品");
            nCell.setCellStyle(thStyle(wb));

            nCell = nRow.createCell(2);
            nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

            nCell = nRow.createCell(3);
            nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

            nCell = nRow.createCell(4);
            nCell.setCellValue(printMap.get("ContractDesc"));
            nCell.setCellStyle(thStyle(wb));

            region = new CellRangeAddress(curRow-1, curRow-1, 5, 6);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(5);
            nCell.setCellValue("数量");
            nCell.setCellStyle(thStyle(wb));

            nCell = nRow.createCell(6);
            nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

            nCell = nRow.createCell(7);
            nCell.setCellValue("单价");
            nCell.setCellStyle(thStyle(wb));

            nCell = nRow.createCell(8);
            nCell.setCellValue("总金额");
            nCell.setCellStyle(thStyle(wb));


            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(96);

            region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格
            sheet.addMergedRegion(region);

            //插入产品图片
            if(UtilFuns.isNotEmpty(printMap.get("ProductImage"))){
                System.out.println(printMap.get("ProductImage"));
                poiUtil.setPicture(wb, patriarch, path+"ufiles/jquery/"+printMap.get("ProductImage"), curRow-1, 1, curRow, 3);
            }

            nCell = nRow.createCell(2);
            nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

            nCell = nRow.createCell(3);
            nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

            //ProductDesc
            region = new CellRangeAddress(curRow-1, curRow, 4, 4);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(4);
            nCell.setCellValue(printMap.get("ProductDesc"));
            nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

            //Cnumber
            region = new CellRangeAddress(curRow-1, curRow, 5, 5);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(5);
            nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            nCell.setCellValue(Double.parseDouble(printMap.get("Cnumber")));
            nCell.setCellStyle(poiUtil.numberrv10_BorderThin(wb, defaultFont10));

            //Unit
            region = new CellRangeAddress(curRow-1, curRow, 6, 6);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(6);
            nCell.setCellValue(printMap.get("PackingUnit"));
            nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));

            //Price
            region = new CellRangeAddress(curRow-1, curRow, 7, 7);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(7);
            nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            nCell.setCellValue(Double.parseDouble(printMap.get("Price")));
            nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));


            //Amount
            region = new CellRangeAddress(curRow-1, curRow, 8, 8);	//纵向合并单元格
            sheet.addMergedRegion(region);

            nCell = nRow.createCell(8);
            if(UtilFuns.isNotEmpty(printMap.get("Cnumber")) && UtilFuns.isNotEmpty(printMap.get("Price"))){
                nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                nCell.setCellFormula("F"+String.valueOf(curRow)+"*H"+String.valueOf(curRow));
            }
            nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));

            curRow++;

            region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格
            sheet.addMergedRegion(region);

            //ProductNo
            nRow = sheet.createRow(curRow-1);
            nRow.setHeightInPoints(24);

            nCell = nRow.createCell(1);
            nCell.setCellValue(printMap.get("ProductNo"));
            nCell.setCellStyle(poiUtil.notecv10_BorderThin(wb, defaultFont10));

            for(int j=2;j<9;j++){
                nCell = nRow.createCell(j);
                nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
            }



            if(contract.getPrint_Style().equals("2") && UtilFuns.isNotEmpty(printMap.get("ProductNo2"))){
                nRow = sheet.createRow(curRow++);
                nRow.setHeightInPoints(96);

                region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格
                sheet.addMergedRegion(region);

                //插入产品图片
                if(UtilFuns.isNotEmpty(printMap.get("ProductImage2"))){
                    System.out.println(printMap.get("ProductImage2"));
                    poiUtil.setPicture(wb, patriarch, path+"ufiles/jquery/"+printMap.get("ProductImage2"), curRow-1, 1, curRow, 3);
                }

                //ProductDesc
                region = new CellRangeAddress(curRow-1, curRow, 4, 4);	//纵向合并单元格
                sheet.addMergedRegion(region);

                nCell = nRow.createCell(4);
                nCell.setCellValue(printMap.get("ProductDesc2"));
                nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));

                //Cnumber
                region = new CellRangeAddress(curRow-1, curRow, 5, 5);	//纵向合并单元格
                sheet.addMergedRegion(region);

                nCell = nRow.createCell(5);
                nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                nCell.setCellValue(Double.parseDouble(printMap.get("Cnumber2")));
                nCell.setCellStyle(poiUtil.numberrv10_BorderThin(wb, defaultFont10));

                //Unit
                region = new CellRangeAddress(curRow-1, curRow, 6, 6);	//纵向合并单元格
                sheet.addMergedRegion(region);

                nCell = nRow.createCell(6);
                nCell.setCellValue(printMap.get("PackingUnit2"));
                nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));

                //Price
                region = new CellRangeAddress(curRow-1, curRow, 7, 7);	//纵向合并单元格
                sheet.addMergedRegion(region);

                nCell = nRow.createCell(7);
                nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                nCell.setCellValue(Double.parseDouble(printMap.get("Price2")));
                nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));


                //Amount
                region = new CellRangeAddress(curRow-1, curRow, 8, 8);	//纵向合并单元格
                sheet.addMergedRegion(region);

                nCell = nRow.createCell(8);
                if(UtilFuns.isNotEmpty(printMap.get("Cnumber2")) && UtilFuns.isNotEmpty(printMap.get("Price2"))){
                    nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                    nCell.setCellFormula("F"+String.valueOf(curRow)+"*H"+String.valueOf(curRow));
                }
                nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));

                curRow++;

                region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格
                sheet.addMergedRegion(region);

                nRow = sheet.createRow(curRow-1);
                nRow.setHeightInPoints(24);

                nCell = nRow.createCell(1);
                nCell.setCellValue(printMap.get("ProductNo2"));
                nCell.setCellStyle(poiUtil.notecv10_BorderThin(wb, defaultFont10));

                //合并单元格画线
                for(int j=2;j<9;j++){
                    nCell = nRow.createCell(j);
                    nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
                }
            }


            //InputBy
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(24);

            nCell = nRow.createCell(1);
            nCell.setCellValue(printMap.get("InputBy"));
            nCell.setCellStyle(poiUtil.bnormalv12(wb,defaultFont12));

            //CheckBy+inspector

            nCell = nRow.createCell(4);
            nCell.setCellValue(printMap.get("CheckBy"));
            nCell.setCellStyle(poiUtil.bnormalv12(wb,defaultFont12));

            //if(contract.getPrintStyle().equals("2") && UtilFuns.isNotEmpty(printMap.get("ProductNo2"))){

            nCell = nRow.createCell(7);
            nCell.setCellValue("总金额：");
            nCell.setCellStyle(bcv12(wb));

            //TotalAmount
            nRow = sheet.createRow(curRow-1);
            nRow.setHeightInPoints(24);
            if(UtilFuns.isNotEmpty(printMap.get("Cnumber"))&&UtilFuns.isNotEmpty(printMap.get("Price"))){
                nCell  = nRow.createCell(8);
                nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                nCell.setCellFormula("SUM(I"+String.valueOf(curRow-4)+":I"+String.valueOf(curRow-1)+")");
                nCell.setCellStyle(poiUtil.moneyrv12_BorderThin(wb,defaultFont12,rmb2Format));
            }
            //}


            //note
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(21);

            nCell = nRow.createCell(2);
            nCell.setCellValue(printMap.get("Remark"));
            nCell.setCellStyle(noteStyle(wb));

            //Crequest
            region = new CellRangeAddress(curRow, curRow, 1, 8);	//指定合并区域
            sheet.addMergedRegion(region);

            nRow = sheet.createRow(curRow++);
            float height = poiUtil.getCellAutoHeight(printMap.get("Crequest"), 12f);		//自动高度
            nRow.setHeightInPoints(height);

            nCell = nRow.createCell(1);
            nCell.setCellValue(printMap.get("Crequest"));
            nCell.setCellStyle(requestStyle(wb));

            //space line
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(20);

            //duty
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(32);

            nCell = nRow.createCell(1);
            nCell.setCellValue("未按以上要求出货而导致客人索赔，由供方承担。");
            nCell.setCellStyle(dutyStyle(wb));

            //space line
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(32);

            //buyer
            nRow = sheet.createRow(curRow++);
            nRow.setHeightInPoints(25);

            nCell = nRow.createCell(1);
            nCell.setCellValue("    收购方负责人：");
            nCell.setCellStyle(dutyStyle(wb));

            //seller
            nCell = nRow.createCell(5);
            nCell.setCellValue("供方负责人：");
            nCell.setCellStyle(dutyStyle(wb));

            curRow++;

        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();			//生成流对象
        wb.write(byteArrayOutputStream);													//将excel写入流

        //工具类，封装弹出下载框：
        String outFile = "购销合同.xls";
        DownloadUtil down = new DownloadUtil();
        down.download(byteArrayOutputStream, response, outFile);

    }

    private HSSFCellStyle leftStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        curStyle.setWrapText(true);  						//换行
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
        //fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)10);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线右边框
        curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);				//实线右边框

        return curStyle;
    }

    private HSSFCellStyle headStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("Comic Sans MS");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setItalic(true);
        curFont.setFontHeightInPoints((short)16);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle tipStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("Georgia");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)28);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle addressStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("宋体");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        //fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)10);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle ltdStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("Times New Roman");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setItalic(true);
        curFont.setFontHeightInPoints((short)16);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle telStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("宋体");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        //fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)9);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle titleStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("黑体");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)18);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle requestStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        curStyle.setWrapText(true);  						//换行
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("宋体");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setFontHeightInPoints((short)10);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle dutyStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("黑体");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)16);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    private HSSFCellStyle noteStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("宋体");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)12);
        curStyle.setFont(curFont);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    public HSSFCellStyle thStyle(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();					//设置字体
        curFont.setFontName("宋体");
        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
        curFont.setFontHeightInPoints((short)12);
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置

        curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线右边框
        curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线右边框
        curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线右边框
        curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线右边框

        curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

    public HSSFCellStyle bcv12(HSSFWorkbook wb){
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont();						//设置字体
        curFont.setFontName("Times New Roman");
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);			//设置中文字体，那必须还要再对单元格进行编码设置

        curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);		//加粗
        curFont.setFontHeightInPoints((short)12);
        curStyle.setFont(curFont);

        curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线
        curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);			//粗实线
        curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
        curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线

        curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中

        return curStyle;
    }

}
