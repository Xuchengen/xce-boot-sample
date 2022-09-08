package com.github.xuchengen.htmlunit;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-08 17:49
 **/
public class TouTiaoTest {
    TouTiaoSignature touTiaoSignature;

    @Before
    public void before() throws Exception {
        touTiaoSignature = new TouTiaoSignature();
    }

    @Test
    public void t1() {
        for (int i = 0; i < 10; i++) {
            String signature = touTiaoSignature.getSignature("https://toutiao.com");
            System.out.println(signature);
        }
    }

}
