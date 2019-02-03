package com.ccc.oa;

import com.ccc.oa.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class UtilTest {
    @Test
    public void test(){
        String password = "password";
        System.out.println(password.toCharArray());
        String aa = MD5Util.encode("123");
        System.out.println(aa);
    }

    @Test
    public void date() {
        Date date = new Date();
        System.out.println(date.toString());

    }
}
