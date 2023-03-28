package com.apktool.utils;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将 Unicode 字符 转成 Utf-8 字符
 * 使用场景
 * - 有时反编译 jar 包后会发现中文乱码，该工具主要解决中文乱码的问题
 */

public class UnicodeToUtf8 {
    public static Collection<File> findJavaFile(String path) {
        Collection<File> listFiles = FileUtils.listFiles(new File(path), FileFilterUtils.suffixFileFilter("java"), DirectoryFileFilter.INSTANCE);
        return listFiles;
    }

    public static void deleteHead(File file) throws IOException {
        String s = FileUtils.readFileToString(file, "utf-8");

        int index = s.indexOf("package");
        s = s.substring(index);
        FileUtil.writeString(s, file, "utf-8");
    }

    public static void findAndChange(File file) throws IOException {
        String s = FileUtil.readString(file, "utf-8");

        Pattern pattern = Pattern.compile("([\\\\][u]....)+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) { // 循环查找匹配
            String unicode = matcher.group();// 打印匹配的子串
            String s1 = asciiToNative(unicode);
            if (unicode.contains("\\u0022")) {
                s1 = s1.replace("\"", "\\\"");
            }
            s = s.replace(unicode, s1);
            System.out.println(String.format("%s %s", unicode, s1));

        }
        FileUtil.writeString(s, file, "utf-8");
    }

    private static String asciiToNative(String asciicode) {
        String[] asciis = asciicode.split("\\\\u");
        String nativeValue = asciis[0];
        try {
            for (int i = 1; i < asciis.length; i++) {
                String code = asciis[i];
                nativeValue += (char) Integer.parseInt(code.substring(0, 4), 16);
                if (code.length() > 4) {
                    nativeValue += code.substring(4, code.length());
                }
            }
        } catch (NumberFormatException e) {
            return asciicode;
        }
        return nativeValue;
    }
}
