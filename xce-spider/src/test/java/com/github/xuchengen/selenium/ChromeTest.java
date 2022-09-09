package com.github.xuchengen.selenium;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-06 13:01
 **/
public class ChromeTest {

    @Test
    public void before() {

    }

    @Test
    public void t1() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setLogLevel(ChromeDriverLogLevel.OFF);
        RemoteWebDriver driver = new RemoteWebDriver(URLUtil.url("https://chrome.tool.xuchengen.cn"), options);
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://toutiao.com");

        driverWait.until(new ExpectedCondition<Object>() {
            private final LongAdder LONG_ADDER = new LongAdder();

            @Override
            public Object apply(WebDriver webDriver) {
                try {
                    if (LONG_ADDER.longValue() > 100) {
                        throw new IllegalAccessError("检测JS失败，超过设定阈值！");
                    }
                    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
                    String str = (String) javascriptExecutor.executeScript("return window.byted_acrawler.sign({url: 'a'});");
                    return StrUtil.isNotBlank(str);
                } catch (Exception e) {
                    return false;
                } finally {
                    LONG_ADDER.increment();
                }
            }
        });

        String script = String.format("return window.byted_acrawler.sign({url: '%s'});", "https://www.toutiao.com");
        for (int i = 0; i < 1000; i++) {
            Object result = driver.executeScript(script);
            System.out.println(result);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
    }

}
