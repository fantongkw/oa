package com.ccc.oa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BCryptTests {
    @Test
    public void test(){
        String pass = "12345678";
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode(pass);
        boolean flag = encode.matches("12313123", hashPass);
        System.out.println(flag);
    }
}
