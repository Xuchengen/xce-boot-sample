package com.github.xuchengen.test;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Websocket
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com</a>
 * <p>日期：2022-10-28 16:07
 **/
public class WebsocketTest {

    @Test
    public void t1() {
        try {
            String url = "wss://streaming.forexpros.com/echo/636/led4eznf/websocket";
            URI uri = new URI(url);
            WebSocketClient webSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }

                @Override
                public void onMessage(String s) {
                    if (s.startsWith("a[\"") && s.endsWith("\"]")) {
                        String f = StrUtil.unWrap(s, "a[\"", "\"]");
                        f = StrUtil.replace(f, "::", ":");
                        f = StrUtil.replace(f, "\\\"message\\\":\\\"", "");
                        f = StrUtil.replace(f, "\\\"}", "}");
                        f = StrUtil.replace(f, "\\\\\\", "");
                        System.out.println(JSONUtil.formatJsonStr(f));
                        System.out.println("==============================================");
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {

                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            };
            webSocketClient.connect();
            // 判断是否连接成功，未成功后面发送消息时会报错
            while (!webSocketClient.getReadyState().equals(ReadyState.OPEN)) {
                System.out.println("连接中···请稍后");
                //noinspection BusyWait
                Thread.sleep(1000);
            }
            webSocketClient.send("{\"_event\":\"bulk-subscribe\",\"tzID\":8,\"message\":\"pid-954694:\"}");
            //webSocketClient.send("{\"_event\":\"UID\",\"UID\": 0}");
            ThreadUtil.execute(() -> {
                while (true) {
                    webSocketClient.send("\"{ \"_event\": \"heartbeat\", \"data\": \"h\"}\"");
                    ThreadUtil.sleep(1000);
                }
            });
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// 交易码表
//8827      美元指数期货
//41064     越南VN30指数
//

//instrument_id变量所指即交易码
//makeRegisterMessage函数

//先发送如下订阅指令
//"{\"_event\":\"bulk-subscribe\",\"tzID\":8,\"message\":\"pid-0:%%isOpenExch-103:%%isOpenExch-54:%%pid-40820:%%pid-28930:%%pid-179:%%isOpenExch-21:%%pid-178:%%isOpenExch-20:%%pid-1175152:%%isOpenExch-152:%%pid-1175153:%%pid-8827:%%isOpenExch-1004:%%pid-8849:%%pid-8833:%%pid-8830:%%pid-8836:%%pid-8862:%%pid-8831:%%pid-8916:%%pid-1:%%isOpenExch-1002:%%pid-2:%%pid-3:%%pid-5:%%pid-7:%%pid-9:%%pid-10:%%pid-6408:%%isOpenExch-2:%%pid-6369:%%pid-243:%%isOpenExch-1:%%pid-267:%%pid-7888:%%pid-284:%%isOpenExch-3:%%pid-352:%%isOpenExch-4:%%pid-941155:%%pid-102047:%%pid-6378:%%pid-102911:%%pid-6435:%%pid-26490:%%pid-169:%%pid-20:%%pid-166:%%pid-172:%%pid-27:%%pid-167:%%isOpenExch-9:%%pid-2111:%%isOpenExch-1001:%%pid-961728:%%pid-1817:%%pid-8832:%%pid-958514:%%isOpenExch-NaN:%%pid-994755:%%pid-958596:%%pid-1043364:%%pid-958482:%%pid-1081734:%%pid-1076910:%%pid-958739:%%pid-41913:%%pid-958566:%%pid-958676:%%pid-958674:%%pid-958561:%%pid-41064:%%pidExt-41064:%%isOpenExch-122:%%cmt-6-5-41064:%%pid-27638:%%pid-13665:%%pid-13994:%%pid-252:%%pid-1031244:%%isOpenExch-125:\"}"
//"{\"_event\":\"UID\",\"UID\": 0}"
}
