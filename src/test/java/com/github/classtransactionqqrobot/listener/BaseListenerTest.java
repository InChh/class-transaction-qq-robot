package com.github.classtransactionqqrobot.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseListenerTest {
    @Autowired
    GroupListener baseListener;

    @Test
    public void baseListenerTest(){
        System.out.println(baseListener);
    }

}