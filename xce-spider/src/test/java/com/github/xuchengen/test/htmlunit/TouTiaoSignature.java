package com.github.xuchengen.test.htmlunit;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-05 22:11
 **/
public final class TouTiaoSignature {

    private static final String URL_ABOUT_PAGE = "https://www.toutiao.com/about/";

    private static final String ACRAWLER_JS = ResourceUtil.readUtf8Str("acrawler.js");

    private static final String SIGNTURE_JS = ResourceUtil.readUtf8Str("signature.js");

    private final WebClient webClient;

    private final HtmlPage page;

    public TouTiaoSignature() throws Exception {
        webClient = new WebClient(BrowserVersion.CHROME);
        WebClientOptions options = webClient.getOptions();
        options.setCssEnabled(false);
        options.setDownloadImages(false);
        options.setAppletEnabled(false);
        options.setActiveXNative(false);
        options.setGeolocationEnabled(false);
        options.setWebSocketEnabled(false);
        options.setUseInsecureSSL(false);
        options.setJavaScriptEnabled(false);
        options.setThrowExceptionOnScriptError(false);
        options.setPopupBlockerEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScript(10000);
        webClient.setJavaScriptErrorListener(new BanJavascriptError());
        page = webClient.getPage(URL_ABOUT_PAGE);
        options.setJavaScriptEnabled(true);
        page.executeJavaScript(ACRAWLER_JS);
    }

    public String getSignature(String targetUrl) {
        String _targetUrl = String.format(SIGNTURE_JS, StrUtil.blankToDefault(targetUrl, StrUtil.EMPTY));
        ScriptResult result = page.executeJavaScript(_targetUrl);
        return result.getJavaScriptResult().toString();
    }

    private static class BanJavascriptError implements JavaScriptErrorListener {

        @Override
        public void scriptException(HtmlPage page, ScriptException scriptException) {

        }

        @Override
        public void timeoutError(HtmlPage page, long allowedTime, long executionTime) {

        }

        @Override
        public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {

        }

        @Override
        public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {

        }

        @Override
        public void warn(String message, String sourceName, int line, String lineSource, int lineOffset) {

        }
    }
}
