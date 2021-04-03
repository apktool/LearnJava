package com.apktool.disruptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 队列中的元素
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Element {
    private int value;
}
