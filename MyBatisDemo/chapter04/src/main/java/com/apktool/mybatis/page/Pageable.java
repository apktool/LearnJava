package com.apktool.mybatis.page;

public interface Pageable {
    /**
     * 获取当前页码
     *
     * @return 当前页码
     */
    int getPageNo();

    /**
     * 获取当前页大小
     *
     * @return 当前页大小
     */
    int getPageSize();
}