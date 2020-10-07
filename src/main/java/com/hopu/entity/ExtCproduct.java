package com.hopu.entity;

import lombok.Data;

@Data
public class ExtCproduct {

    private String EXT_CPRODUCT_ID;

    private ContractPro contract_product_c;

    private String CONTRACT_PRODUCT_ID;

    private String factory_Id;

    private String factory_Name;

    private String ctype;

    private String product_No;

    private String product_Image;

    private String product_Desc;

    private Integer cnumber;

    private String packing_Unit;

    private double price;

    private double amount;

    private String product_Request;

    private Integer order_No;

}
