package com.github.xuchengen.test.youtube;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-09 16:18
 **/
public class YouTubeTest {

    private static final String KEY = "Your Key";

    @Test
    public void t1() throws Exception {
        YouTube youTubeClient = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(), null)
                .setApplicationName("江峰剧场")
                .build();

        YouTube.Channels.List request = youTubeClient.channels()
                .list(Collections.singletonList("snippet,contentDetails,statistics"));

        ChannelListResponse response = request
                .setId(Collections.singletonList("UCa6ERCDt3GzkvLye32ar89w"))
                .setKey(KEY)
                .execute();

        System.out.println(response);

        List<Channel> items = response.getItems();
        for (Channel item : items) {
            YouTube.Playlists.List playListsRequest = youTubeClient.playlists()
                    .list(Collections.singletonList("contentDetails,id,localizations,player,snippet,status"));

            PlaylistListResponse playListsResponse = playListsRequest
                    .setChannelId("UCa6ERCDt3GzkvLye32ar89w")
                    .setKey(KEY)
                    .setMaxResults(50L)
                    .execute();

            System.out.println(playListsResponse);
        }
    }

}
