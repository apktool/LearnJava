package com.apktool.utils;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;


public class UnicodeToUtf8Test {
    @Test
    public void test01() throws IOException {
        String path = "/home/li/Workspace/abc";
        Collection<File> javaFiles = UnicodeToUtf8.findJavaFile(path);
        for (File file : javaFiles) {
            UnicodeToUtf8.deleteHead(file);
            System.out.println("删除完成");
        }
        Collection<File> javaFiles2 = UnicodeToUtf8.findJavaFile(path);
        for (File file : javaFiles2) {
            UnicodeToUtf8.findAndChange(file);
            System.out.println("修改完成");
        }

    }
}