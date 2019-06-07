package com.apktool.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author apktool
 * @date 2019-02-17 17:04
 */
@Setter
@Getter
@ExcelTarget("studentEntity")
public class StudentEntity implements Serializable {
    private String id;

    @Excel(name = "学生姓名", height = 20, width = 20, isImportField = "true")
    private String name;

    @Excel(name = "学生性别", replace = {"男_1", "女_2"}, suffix = "生", isImportField = "true")
    private int sex;

    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss", isImportField = "true", width = 20)
    private Date birthday;

    @Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
    private Date registrationDate;
}
