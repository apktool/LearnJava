package com.apktool.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author apktool
 * @date 2019-02-17 17:47
 */
@Setter
@Getter
@ExcelTarget("teacherEntity")
public class TeacherEntity implements Serializable {

    private String id;

    @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1", isImportField = "true_major,true_absent", needMerge = true)
    private String name;
}
