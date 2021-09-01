package com.github.classtransactionqqrobot.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
class TimeUtilTest {

    @Test
    void timeCheck() {
        final boolean b = TimeUtil.timeCheck();
        System.out.println(b);
    }
}