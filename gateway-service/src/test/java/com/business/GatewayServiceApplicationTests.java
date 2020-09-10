package com.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class GatewayServiceApplicationTests {

    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("123456"));
    }

}
