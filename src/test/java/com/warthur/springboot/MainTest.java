package com.warthur.springboot;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author warthur
 * @date 2020/12/12
 */
public class MainTest {

    @Test
    public void testDate() {

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("")));
    }
}
