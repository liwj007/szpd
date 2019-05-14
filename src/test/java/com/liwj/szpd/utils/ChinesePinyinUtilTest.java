package com.liwj.szpd.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChinesePinyinUtilTest {

    @Test
    public void getPinYinHeadChar() {
        String cnStr = "重庆,重视昭君发展(专科)环-境喵邈";
        System.out.println(ChinesePinyinUtil.getPinYinFirstHeadChar(cnStr));
    }
}