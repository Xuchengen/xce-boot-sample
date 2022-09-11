package com.github.xuchengen.test;

import cn.hutool.json.JSONUtil;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-11 13:34
 **/
public class CloudinaryTest {

    Cloudinary cloudinary;

    @Before
    public void before() {
        cloudinary = new Cloudinary("Your key");
    }

    @Test
    public void t1() throws Exception {
        String url = "https://pics7.baidu.com/feed/4034970a304e251f1a06ba92765c251d7d3e53c8.jpeg";
        Map map = cloudinary.uploader().upload(url, ObjectUtils.emptyMap());
        System.out.println(JSONUtil.toJsonStr(map));
    }

    @Test
    public void t2() throws Exception {
        String s = cloudinary.downloadFolder("/", ObjectUtils.emptyMap());
        System.out.println(s);
    }

}
