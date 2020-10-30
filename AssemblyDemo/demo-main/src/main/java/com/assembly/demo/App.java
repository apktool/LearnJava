package com.assembly.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author apktool
 * @package com.assembly.demo
 * @class App
 * @date 2020-10-30 21:40:51
 */

@Slf4j
public class App {
    public static void main(String[] args) {
        log.trace("-> Trace");
        log.debug("-> Debug");
        log.info("-> Info");
        log.warn("-> Warn");
        log.error("-> Warn");
    }
}
