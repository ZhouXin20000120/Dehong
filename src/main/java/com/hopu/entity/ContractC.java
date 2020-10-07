package com.hopu.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * (ContractC)实体类
 *
 * @author makejava
 * @since 2020-09-22 17:34:59
 */
@Data
public class ContractC {

    private String contract_Id;

    private String offeror;

    private String contract_No;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signing_Date;

    private String input_By;

    private String check_By;

    private String inspector;

    private double total_Amount;

    private String request;

    private String custom_Name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ship_Time;

    private Integer import_Num;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delivery_Period;

    private String remark;

    private String print_Style;

    private Integer old_State;

    private Integer state;

    private Integer out_State;

    private String create_By;

    private String create_Dept;

    private String cpnum;

    private ContractPro contractPro;

    private String extnum;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_Time;
}