package com.ender.maven.helloworld;

/**
 * Created by meihl on 2018/3/18.
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HellowWorldTest {

    @Test
    public void testSayHello(){
        HelloWorld helloWorld = new HelloWorld();
        String result=helloWorld.sayHello();
        assertEquals("Hello Maven",result);
    }

}
