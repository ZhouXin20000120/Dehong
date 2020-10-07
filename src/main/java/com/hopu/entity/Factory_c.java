package com.hopu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Factory_c {

    String factory_id;

    String full_name;

    String factory_name;

    String contactor;

    String phone;

    String mobile;

    String fax;

    String cnote;

    Integer ctype;

    String state;

    String inspector;

    Integer order_no;

    String create_by;

    String create_dept;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date create_time;



}
