package com.github.classtransactionqqrobot.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseListenerTest {
    @Autowired
    BaseListener baseListener;

    @Test
    public void baseListenerTest(){
        System.out.println(baseListener);
    }

}