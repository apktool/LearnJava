package com.apktool.hbase.exception;

import java.io.IOException;

public class ApktoolIoException extends IOException {
    public ApktoolIoException(String message) {
        super(message);
    }

    public ApktoolIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApktoolIoException(Throwable cause) {
        super(cause);
    }
}
