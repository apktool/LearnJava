package com.deplayedtask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author apktool
 * @date 2018-06-27 15:18
 */
class AppTestTest {
    AppTest appTest = new AppTest();

    @BeforeEach
    void productionDelayMessage() {
        appTest.productionDelayMessage();
    }

    @Test
    void consumerDelayMessage() {
        appTest.consumerDelayMessage();
    }
}