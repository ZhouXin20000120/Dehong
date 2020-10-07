package com.hopu.entity;

import lombok.Data;

/**
 * (ExportProductC)实体类
 *
 * @author makejava
 * @since 2020-09-23 13:53:54
 */
@Data
public class Export  {

    private String EXPORT_PRODUCT_ID;

    private String contract_Product_Id;

    private String export_Id;

    private Factory_c factory_c;

    private String factory_id;

    private String contract_Id;

    private String contract_No;

    private String product_Name;

    private String product_No;

    private String product_Image;

    private String product_Desc;

    private String loading_Rate;

    private String packing_Unit;

    private Integer cnumber;

    private Integer out_Number;

    private Integer finished;

    private double gross_Weight;

    private double net_Weight;

    private double size_Lenght;

    private double size_Width;

    private double size_Height;

    private String product_Request;

    private String factory;

    private double price;

    private double amount;

    private String cunit;

    private Integer box_Num;

    private double ex_Price;

    private String ex_Unit;

    private double no_Tax;

    private double tax;

    private double cost_Price;

    private double cost_Tax;

    private Integer accessories;

    private Integer order_No;


}