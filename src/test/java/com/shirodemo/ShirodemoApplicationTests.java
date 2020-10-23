package com.shirodemo;

import com.shirodemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShirodemoApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads(){
        System.out.println(userService.findUserByName("111"));
    }

}
