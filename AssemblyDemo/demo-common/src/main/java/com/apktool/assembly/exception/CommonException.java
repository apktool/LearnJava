package com.apktool.assembly.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author apktool
 * @package com.apktool.demo.exception
 * @class CommonException
 * @date 2020-10-22 23:55:10
 */

@Slf4j
public class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
