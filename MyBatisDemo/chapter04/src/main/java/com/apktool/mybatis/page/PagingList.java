package com.apktool.mybatis.page;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagingList<E> extends ArrayList<E> {
    private long total;
}