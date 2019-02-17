package com.apktool.main;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.apktool.entity.CourseEntity;
import com.apktool.entity.StudentEntity;
import com.apktool.entity.TeacherEntity;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author apktool
 * @date 2019-02-17 17:12
 */
public class ExcelExport {
    public static void main(String[] args) throws IOException {

        StudentEntity stu1 = new StudentEntity();
        stu1.setName("撒旦法司法局");
        stu1.setSex(1);
        stu1.setBirthday(new Date());

        StudentEntity stu2 = stu1;

        List<StudentEntity> studentEntityList = new ArrayList<StudentEntity>();
        studentEntityList.add(stu1);
        studentEntityList.add(stu2);

        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), StudentEntity.class, studentEntityList);

        FileOutputStream fos1 = new FileOutputStream("/tmp/easypoi-student-1.xlsx");
        workbook1.write(fos1);
        fos1.close();

        // -------------------------------------------

        studentEntityList.add(stu1);
        studentEntityList.add(stu2);

        TeacherEntity teacherEntity1 = new TeacherEntity();
        teacherEntity1.setName("老王0");

        CourseEntity courseEntity1 = new CourseEntity();
        courseEntity1.setId("1");
        courseEntity1.setName("海贼王必修(1)");
        courseEntity1.setMathTeacher(teacherEntity1);
        courseEntity1.setStudents(studentEntityList);

        TeacherEntity teacherEntity2 = new TeacherEntity();
        teacherEntity2.setName("老王1");

        CourseEntity courseEntity2 = new CourseEntity();
        courseEntity2.setId("2");
        courseEntity2.setName("海贼王必修(2)");
        courseEntity2.setMathTeacher(teacherEntity2);
        courseEntity2.setStudents(studentEntityList);


        List<CourseEntity> courseEntityList = new ArrayList<CourseEntity>();
        courseEntityList.add(courseEntity1);
        courseEntityList.add(courseEntity2);

        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), CourseEntity.class, courseEntityList);
        FileOutputStream fos2 = new FileOutputStream("/tmp/easypoi-course-1.xlsx");
        workbook2.write(fos2);
        fos2.close();
    }
}
