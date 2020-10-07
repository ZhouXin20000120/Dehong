package com.hopu.entity;

import lombok.Data;

import java.util.Date;

/**
 * (SysCodeB)实体类
 *
 * @author makejava
 * @since 2020-09-25 09:15:19
 */
@Data
public class SysCodeB  {

    private String sys_Code_Id;

    private String name;

    private String parent_Id;

    private String parent_Name;

    private Integer layer_Num;

    private Integer isLeaf;

    private Integer quote_Num;

    private String cnote;

    private String ico;

    private Integer order_No;

    private String state;

    private String created_By;

    private Date created_Time;

    private String updated_By;

    private Date updated_Time;

}