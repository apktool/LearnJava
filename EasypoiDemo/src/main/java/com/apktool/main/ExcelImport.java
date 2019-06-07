package com.apktool.main;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.apktool.entity.CourseEntity;
import com.apktool.entity.StudentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * @author apktool
 * @date 2019-02-17 18:26
 */
public class ExcelImport {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelImport.class);

    public static void main(String[] args) {
        ImportParams params1 = new ImportParams();
        params1.setHeadRows(1);
        params1.setTitleRows(1);

        List<StudentEntity> studentEntityList = ExcelImportUtil.importExcel(new File("/tmp/easypoi-student-1.xlsx"), StudentEntity.class, params1);
        studentEntityList.forEach(item -> LOGGER.info(item.getName() + " | " + item.getSex() + " | " + item.getBirthday()));

        ImportParams params2 = new ImportParams();
        params2.setHeadRows(3);
        List<CourseEntity> courseEntityList = ExcelImportUtil.importExcel(new File("/tmp/easypoi-course-1.xlsx"), CourseEntity.class, params2);

        courseEntityList.forEach(t ->
                t.getStudents().forEach(s -> {
                    LOGGER.info(t.getName() + " | " + t.getMathTeacher().getName() + " | " + s.getName() + " | " + s.getSex() + " | " + s.getBirthday());
                })
        );
    }
}
