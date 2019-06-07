package com.apktool.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author apktool
 * @date 2019-02-17 17:49
 */
@Setter
@Getter
@ExcelTarget("courseEntity")
public class CourseEntity implements Serializable {
    @Excel(name = "序号")
    private String id;

    @Excel(name = "课程名称", orderNum = "1", needMerge = true)
    private String name;

    @ExcelEntity(id = "absent")
    private TeacherEntity mathTeacher;

    @ExcelCollection(name = "学生", orderNum = "3")
    private List<StudentEntity> students;
}
